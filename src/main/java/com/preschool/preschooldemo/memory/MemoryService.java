package com.preschool.preschooldemo.memory;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.preschool.preschooldemo.common.exception.AuthErrorCode;
import com.preschool.preschooldemo.common.exception.RestApiException;
import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.utils.Const;
import com.preschool.preschooldemo.common.utils.MyFileUtils;

import com.preschool.preschooldemo.common.utils.ResVo;
import com.preschool.preschooldemo.memory.model.*;
import com.preschool.preschooldemo.notice.model.NoticePushVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private final ObjectMapper objMapper;

    public AllMemoryVo allMemory(AllSelMemoryDto dto){
        List<String> roles = authenticationFacade.getRoles();
        AllMemoryVo vo = new AllMemoryVo();
        if(roles.get(0).equals("TEACHER") || roles.get(0).equals("ADMIN")){
            List<AllSelMemoryVo> list = mapper.allMemoryTea(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryComment(mapper.iMemoryComment(list.get(i).getImemory()));
                list.get(i).setIkids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryTeaCnt(dto));
        }
        if(roles.get(0).equals("USER") || roles.get(0).equals("GRADUATE")){
            List<AllSelMemoryVo> list = mapper.allMemoryPar(dto);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setMemoryComment(mapper.iMemoryComment(list.get(i).getImemory()));
                list.get(i).setIkids(mapper.iMemoryIkid(list.get(i).getImemory()));
            }
            vo.setList(list);
            vo.setImemoryCnt(mapper.allMemoryParCnt(dto));
        }
        return vo;
    }

    public AllSelMemoryVo memory(int imemory){
        return null;
    }


    public SelMemoryVo selMemory(int imemory){

        List<String> roles = authenticationFacade.getRoles();
        if(!(roles.get(0).equals("ADMIN") || roles.get(0).equals("TEACHER"))){
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }

        String exist = mapper.selImemory(imemory);
        if(exist ==null){
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        if(imemory < 0 ){
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        SelMemoryVo memory = mapper.selMemory(imemory);
        List<String> pics = mapper.selMemoryPic(imemory);
        memory.setMemoryPic(pics);


        return memory;
    }
    //-------------------------------- 추억 앨범 등록 --------------------------------
    public ResVo postMemory(List<MultipartFile> pics, InsMemoryDto dto){

        int iuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();

        if(level == Const.TEACHER || level == Const.BOSS){
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        if (pics.size()>20){
            throw new RestApiException(AuthErrorCode.MANY_PIC);
        }
        if (pics.size() == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.PICS_NULL);
        }

        dto.setIteacher(iuser);
        int result = mapper.insMemory(dto);
        if(result == Const.ZERO){
            throw new RestApiException(AuthErrorCode.POST_FAIL);
        }

        InsRoomInviteProcDto pdto = InsRoomInviteProcDto.builder()
                .imemory(dto.getImemory())
                .ikids(dto.getIkids())
                .build();

        int invite = mapper.insMemoryRoomInvite(pdto);
        if (invite == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }

        InsMemoryPicsDto picsDto = new InsMemoryPicsDto();
        picsDto.setImemory(dto.getImemory());
        String target = "/memory/" + dto.getImemory();

        for (MultipartFile file : pics) {
            String saverFileNm = myFileUtils.transferTo(file, target);
            picsDto.getMemoryPics().add(saverFileNm);
        }
        int picResult = mapper.insMemoryPic(picsDto);

        if (picResult < 1) {
            throw new RestApiException(AuthErrorCode.PICS_FAIL);
        }

        LocalDateTime now = LocalDateTime.now(); // 현재 날짜 구하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 포맷 정의
        String createdAt = now.format(formatter); // 포맷 적용

        List<SelMemoryOtherTokens> otherTokens = new ArrayList<>();
        otherTokens = mapper.selParFirebaseByLoginUser(dto.getIkids());

        if (otherTokens != null) {
            try {

                otherTokens.removeAll(Collections.singletonList(null));
                for (SelMemoryOtherTokens token : otherTokens) {

                    MemoryPushVo pushVo = new MemoryPushVo();
                    pushVo.setMemoryTitle(dto.getMemoryTitle());
                    pushVo.setWriterIuser(iuser);
                    pushVo.setCreatedAt(createdAt);
                    pushVo.setIkid(token.getIkid());
                    pushVo.setKidNm(token.getKidNm());
                    pushVo.setImemory(dto.getImemory());

                    String body = objMapper.writeValueAsString(pushVo);
                    log.info("body: {}", body);
                    Notification noti = Notification.builder()
                            .setTitle("postMemory")
                            .setBody(body)
                            .build();

                    Message message = Message.builder()
                            .setToken(token.getFirebaseToken())
                            .setNotification(noti)
                            .build();

                    FirebaseMessaging.getInstance().sendAsync(message);
                }
            } catch (Exception e) {
                throw new RestApiException(AuthErrorCode.PUSH_FAIL);
            }
        }

        return new ResVo(dto.getImemory());
    }

    public ResVo delMemoryComment(DelMemoryCommentDto dto){

        if ((dto.getIparent() == 0 && dto.getIteacher() == 0) ||
                (dto.getIparent() > 0 && dto.getIteacher() > 0)) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }

        int result = mapper.delMemoryComment(dto);

        if (result == 0) {
            return new ResVo(Const.NO_INFORMATION);
        }
        return new ResVo(result);
    }

}
