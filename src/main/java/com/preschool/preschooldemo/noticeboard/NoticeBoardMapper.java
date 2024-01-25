package com.preschool.preschooldemo.noticeboard;

import com.preschool.preschooldemo.noticeboard.model.*;
import com.preschool.preschooldemo.teacher.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeBoardMapper {
//------------------------ 학부모 유저 시점 원아 알림장 전체 조회 ------------------------
    List<SelAllNoticeVo> selAllNoticeBoardPar(SelAllNoticeDto dto);
//------------------------ 선생님 유저 시점 원아 알림장 전체 조회 ------------------------
    List<SelAllNoticeVo> selAllNoticeBoardTea(SelAllNoticeDto dto);
//------------------------ 알림장 전체 조회 시 해당 알림장의 사진 유/무 체크 ------------------------
    Integer selNoticeBoardPicCheck(int inotice);
//------------------------ 알림장 상세 조회 ------------------------
    SelDetailNoticeVo selNoticeDetail(int inotice);
//------------------------ 알림장 댓글 정보 조회 ------------------------
    List<SelNoticeComment> selNoticeDetailCom(int inotice);
//------------------------ 알림장 작성자 pk로 이름 조회(관리자) ------------------------
    SelNoticeCommentProc selNoticeDetailTea (int iteacher);
//------------------------ 알림장 작성자 pk로 이름 조회(부모님) ------------------------
    SelNoticeCommentProc selNoticeDetailPar (int iparent);
//------------------------ 알림장 사진 조회 ------------------------
    List<String> selNoticeDetailPics(int notice);
//-------------------------------- 알림장 댓글 등록 --------------------------------
    int insNoticeComment (InsNoticeCommentDto dto);
//-------------------------------- 알림장 댓글 삭제 --------------------------------
    int delNoticeComment (DelNoticeCommentDto dto);
}
