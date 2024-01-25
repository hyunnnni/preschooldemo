package com.preschool.preschooldemo.noticeboard;

import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.exception.AuthErrorCode;
import com.preschool.preschooldemo.exception.CommonErrorCode;
import com.preschool.preschooldemo.exception.RestApiException;
import com.preschool.preschooldemo.noticeboard.model.*;
import com.preschool.preschooldemo.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class NoticeBoardService {
    private final NoticeBoardMapper mapper;
    private final AuthenticationFacade authenticationFacade;

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    public List<SelAllNoticeVo> getKidManagement(SelAllNoticeDto dto) {

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            List<SelAllNoticeVo> voList = new ArrayList<>();
            SelAllNoticeVo vo = new SelAllNoticeVo();

            if (dto.getIlevel() == 1) {
                voList = mapper.selAllNoticeBoardPar(dto);
            }
            if (dto.getIlevel() == 2 || dto.getIlevel() == 3) {
                voList = mapper.selAllNoticeBoardTea(dto);
            }
            if (voList.size() == 0) {
                throw new RestApiException(AuthErrorCode.NO_INFORMATION);
            }

            for (SelAllNoticeVo picCheck : voList) {
                Integer result = mapper.selNoticeBoardPicCheck(picCheck.getInotice());
                if (result != null) {
                    picCheck.setPicCheck(Const.SUCCESS);
                }
            }

            return voList;

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //-------------------------------- 알림장 상세 조회 --------------------------------
    public SelDetailNoticeVo getDetailNotice(SelDetailNoticeDto dto) {

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            SelDetailNoticeVo vo = mapper.selNoticeDetail(dto.getInotice());

            if (vo == null) {
                throw new RestApiException(AuthErrorCode.NO_INFORMATION);
            }

            vo.setPics(mapper.selNoticeDetailPics(dto.getInotice()));

            List<SelNoticeComment> comList = mapper.selNoticeDetailCom(dto.getInotice());
            List<SelNoticeCommentProc> comments = new ArrayList<>();

            if (comList.size() > 0) {
                for (SelNoticeComment com : comList) {
                    if (com.getIparent() > 0 && com.getIteacher() == 0) {
                        SelNoticeCommentProc parComment = mapper.selNoticeDetailPar(com.getIparent());
                        parComment.setInoticeComment(com.getInoticeComment());
                        parComment.setNoticeComment(com.getNoticeComment());
                        parComment.setCreatedAt(com.getCreatedAt());
                        comments.add(parComment);
                    }
                    if (com.getIteacher() > 0 && com.getIparent() == 0) {
                        SelNoticeCommentProc teaComment = mapper.selNoticeDetailTea(com.getIteacher());
                        teaComment.setInoticeComment(com.getInoticeComment());
                        teaComment.setNoticeComment(com.getNoticeComment());
                        teaComment.setCreatedAt(com.getCreatedAt());
                        comments.add(teaComment);
                    }

                }
            }
            vo.setComments(comments);

            return vo;
        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
//-------------------------------- 알림장 댓글 등록 --------------------------------
    public ResVo postNoticeComment(InsNoticeCommentDto dto) {
        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            int result = mapper.insNoticeComment(dto);
            if (result == 0) {
                return new ResVo(Const.FAIL);
            }
            return new ResVo(result);
        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

//-------------------------------- 알림장 댓글 삭제 --------------------------------
    public ResVo delNoticeComment(DelNoticeCommentDto dto) {
        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if ((dto.getIparent() == 0 && dto.getIteacher() == 0) ||
                    (dto.getIparent() > 0 && dto.getIteacher() > 0)) {
                return new ResVo(Const.BAD_PARAMETER);
            }

            int result = mapper.delNoticeComment(dto);

            if (result == 0) {
                return new ResVo(Const.NO_INFORMATION);
            }

            return new ResVo(result);
        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
