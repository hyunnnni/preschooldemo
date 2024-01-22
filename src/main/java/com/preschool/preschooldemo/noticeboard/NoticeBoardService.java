package com.preschool.preschooldemo.noticeboard;

import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.noticeboard.model.*;
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

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    public List<SelAllNoticeVo> getKidManagement(SelAllNoticeDto dto) {

        List<SelAllNoticeVo> voList = new ArrayList<>();
        SelAllNoticeVo vo = new SelAllNoticeVo();

        if (dto.getIlevel() == 1) {
            voList = mapper.selAllNoticeBoardPar(dto);
        }
        if (dto.getIlevel() == 2 || dto.getIlevel() == 3) {
            voList = mapper.selAllNoticeBoardTea(dto);
        }
        if (voList.size() == 0) {
            vo.setResult(Const.NO_INFORMATION);
            voList.add(vo);
            return voList;
        }

        for (SelAllNoticeVo picCheck : voList) {
            Integer result = mapper.selNoticeBoardPicCheck(picCheck.getInotice());
            if (result != null) {
                picCheck.setPicCheck(Const.SUCCESS);
            }
        }

        return voList;
    }

    //-------------------------------- 알림장 상세 조회 --------------------------------
    public SelDetailNoticeVo getDetailNotice(SelDetailNoticeDto dto) {

        SelDetailNoticeVo vo = mapper.selNoticeDetail(dto.getInotice());

        if (vo == null) {
            SelDetailNoticeVo vo1 = new SelDetailNoticeVo();
            vo1.setResult(Const.NO_INFORMATION);
            return vo1;
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
    }
//-------------------------------- 알림장 댓글 등록 --------------------------------
    public ResVo postNoticeComment(InsNoticeCommentDto dto) {
        int result = mapper.insNoticeComment(dto);
        if(result == 0){
            return new ResVo(Const.FAIL);
        }
        return new ResVo(result);
    }

//-------------------------------- 알림장 댓글 삭제 --------------------------------
    public ResVo delNoticeComment(DelNoticeCommentDto dto) {

        if((dto.getIparent() == 0 && dto.getIteacher() == 0) ||
           (dto.getIparent() > 0 && dto.getIteacher() > 0)){
            return new ResVo(Const.BAD_PARAMETER);
        }

        int result = mapper.delNoticeComment(dto);

        if(result == 0){
            return new ResVo(Const.NO_INFORMATION);
        }

        return new ResVo(result);
    }
}
