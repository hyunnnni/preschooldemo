package com.preschool.preschooldemo.memory;


import com.preschool.preschooldemo.common.exception.AuthErrorCode;
import com.preschool.preschooldemo.common.exception.RestApiException;
import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.utils.Const;
import com.preschool.preschooldemo.common.utils.MyFileUtils;

import com.preschool.preschooldemo.common.utils.ResVo;
import com.preschool.preschooldemo.memory.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

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

        //글등록 선생님만 제한
        //사진 개수 체크
        //글등록
        //사진 등록
        //원아는 리스트로 태그 해당 부모로 셀렉 후 글 등록
        //푸시 기능

        int iuser = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        if(level == Const.TEACHER || level == Const.BOSS){
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        if (pics.size()>20){
            throw new RestApiException(AuthErrorCode.MANY_PIC);
        }

        dto.setIteacher(iuser);
        int result = mapper.insMemory(dto);
        if(result == Const.ZERO){
            throw new RestApiException(AuthErrorCode.);
        }
        List<Integer> iparent = mapper.selKidConnectPar(dto.getIkids());

        if (iparent.size() != Const.ZERO){

            InsRoomInviteProcDto pdto = InsRoomInviteProcDto.builder()
                    .imemory(dto.getImemory())
                    .iparents(iparent)
                    .build();

            int invite = mapper.insMemoryRoomInvite(pdto);
            if(invite == Const.ZERO){
                throw new RestApiException(AuthErrorCode.FAIL);
            }
        }

return null;
     }

}
