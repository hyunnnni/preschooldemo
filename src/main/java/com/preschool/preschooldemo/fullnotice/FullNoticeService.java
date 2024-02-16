package com.preschool.preschooldemo.fullnotice;

import com.preschool.preschooldemo.common.exception.AuthErrorCode;
import com.preschool.preschooldemo.common.exception.RestApiException;
import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.utils.Const;
import com.preschool.preschooldemo.common.utils.MyFileUtils;
import com.preschool.preschooldemo.fullnotice.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.preschool.preschooldemo.common.utils.ResVo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FullNoticeService {
    private final FullNoticeMapper mapper;
    private final MyFileUtils mfu;
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;

    //-------------------------------- 전체 게시판 조회 --------------------------------

    public AllFullNoticeSelVo getAllFullNotice(SelFullNoticeDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        List<SelFullNoticeVo> noticeFix = mapper.getFullNoticeFix(dto.getSearch());
        if (noticeFix.size() > 3) {
            throw new RestApiException(AuthErrorCode.NOT_IMPORTED);
        }

        dto.setRow(Const.NOTICE_COUNT_PER_PAGE - noticeFix.size());

        //size 0일때 예외처리, 두개값 합쳤을시 10 초과나 미만일시 예외처리
        List<SelFullNoticeVo> noticelist = mapper.getFullNoticelist(dto);
        if (noticelist.size() == 0) {
            throw new RestApiException(AuthErrorCode.NOT_IMPORTED);
        }
        List<SelFullNoticeVo> voList = new ArrayList<>();
        voList.addAll(noticeFix);
        voList.addAll(noticelist);
        if (voList.size() > 10) {
            throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
        }
        AllFullNoticeSelVo vo = new AllFullNoticeSelVo();
        vo.setList(voList);
        int noticeCnt = mapper.selNoticeCnt(dto.getSearch());
        vo.setNoticeCnt(noticeCnt);
        int noticeFixCnt = mapper.selNoticeFixCnt(dto.getSearch());
        vo.setNoticeFixCnt(noticeFixCnt);
        return vo;
    }

    //-------------------------------- 개별게시판 조회 --------------------------------
    //우선 받은값들을 가져와서 사진여러개를 공지사항 안에 넣어줘야함

    public SelNoticeVo getFullNotice(int iFullNotice) {


        SelNoticeVo notice = mapper.getNotice(iFullNotice);
        if(notice == null){
            throw new RestApiException(AuthErrorCode.NOT_IMPORTED);
        }

        notice.setPics(mapper.selNoticePic(iFullNotice));

        return notice;
    }


//-------------------------------- 유치원 소식 작성 --------------------------------

    @Transactional
    public ResVo postFullNotice (InsFullNoticeDto dto){

            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

            if (fix >= 3 && dto.getFullNoticeFix() == 1) {
                throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
            }

            int result = mapper.insFullNotice(dto);
            InsFullPicsDto pdto = new InsFullPicsDto();

            if (result == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            if (dto.getFullPic() == null) {
                return new ResVo(Const.SUCCESS);
            }
            if(dto.getFullPic().size() > 20) {
                throw new RestApiException(AuthErrorCode.MANY_PIC);
            }

            pdto.setIfullNotice(dto.getIfullNotice());
            String target = "/fullnotice/" + dto.getIfullNotice();

            for (MultipartFile file : dto.getFullPic()) {
                String saverFileNm = mfu.transferTo(file, target);
                pdto.getFullPic().add(saverFileNm);
            }

            int picResult = mapper.insFullNoticePics(pdto);

            if (picResult < 1) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }

            return new ResVo(dto.getIfullNotice());
    }

//-------------------------------- 유치원 소식 삭제 --------------------------------

    @Transactional
    public ResVo delFullNotice(DelFullNoticeDto dto){

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            throw new RestApiException(AuthErrorCode.NO_PERMISSION);
        }

        int result = mapper.delFullNoticePics(dto);

        int result1 = mapper.delFullNotice(dto);

        if (result1 == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        return new ResVo(Const.SUCCESS);
    }

//-------------------------------- 유치원 소식 수정 --------------------------------

    @Transactional
    public ResVo putFullNotice(UpdFullNoticeDto dto){

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            throw new RestApiException(AuthErrorCode.NO_PERMISSION);
        }

        Integer fix = mapper.selNoticeFix(dto.getIfullNotice());

        if (fix >= 3 && dto.getFullNoticeFix() == 1) {
            throw new RestApiException(AuthErrorCode.OVER_FIX_NOTICE);
        }

        int result = mapper.putFullNotice(dto);
        Integer selResult = mapper.selFullNoticePics(dto.getIfullNotice());
        InsFullPicsDto pdto = new InsFullPicsDto();

        if (result == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        if (selResult > 0 && dto.getDelPics() != null) {
            int delResult = mapper.delUpdFullNoticePics(dto.getDelPics());
            if (delResult == 0) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }
        }
        if (dto.getFullPic() == null) {
            return new ResVo(Const.SUCCESS);

        }
        selResult = mapper.selFullNoticePics(dto.getIfullNotice());
        int picSize = 20;
        if ((picSize - selResult) < dto.getFullPic().size()){
            throw new RestApiException(AuthErrorCode.MANY_PIC);
        }

        pdto.setIfullNotice(dto.getIfullNotice());
        String target = "/fullnotice/" + dto.getIfullNotice();
        myFileUtils.delFolderTrigger(target);
        for (MultipartFile file : dto.getFullPic()) {
            String saverFileNm = mfu.transferTo(file, target);
            pdto.getFullPic().add(saverFileNm);
        }

        int picResult = mapper.insFullNoticePics(pdto);

        if (picResult < 1) {
            throw new RestApiException(AuthErrorCode.PICS_FAIL);
        }
        return new ResVo(dto.getIfullNotice());

    }
//-------------------------------- 유치원 소식 수정 시 불러오기 --------------------------------

    public SelFullNoticeUpdVo getFullNoticeUpd(SelFullNoticeUpdDto dto){

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            throw new RestApiException(AuthErrorCode.NO_PERMISSION);
        }

        SelFullNoticeUpdVo vo = mapper.selFullNoticeUpd(dto.getIfullNotice());

        if (vo == null) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        List<String> picList = mapper.selFullNoticeUpdPics(dto.getIfullNotice());

        vo.setFullPic(picList);

        return vo;
    }
}
