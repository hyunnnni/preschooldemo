package com.preschool.preschooldemo.notice;

import com.preschool.preschooldemo.notice.model.*;
import com.preschool.preschooldemo.notice.model.NoticeUpdSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //------------------------ 알림장 등록 글 ------------------------
    int insNotice(NoticeInsProcDto pdto);
    //------------------------ 알림장 등록 사진 ------------------------
    int insNoticePics(NoticePicsInsDto dto);
    //------------------------ 알림장 작성 시 푸시를 받을 학부모의 토큰값과 pk값 조회 ------------------------
    List<String> selParFirebaseByLoginUser(List<Integer> inotices);
    //------------------------ 알림장 작성 시 푸시를 받을 선생님의 토큰값과 pk값 조회 ------------------------
    List<String> selTeaFirebaseByLoginUser(List<Integer> inotices);
    //------------------------ 알림장 수정 시 내용 조회 글 ------------------------
    NoticeUpdSelVo noticeEdit(int inotice, int ikid);
    //------------------------ 알림장 수정 시 내용 조회 사진 ------------------------
    List<NoticePicsVo> noticeEditPics(int inotice);
    //------------------------ 알림장 수정 ------------------------
    List<Integer> noticeSelPics(int inotice);
    int updNotice(NoticeUpdDto dto);
    //------------------------ 수정 시 알림장 사진 삭제 ------------------------
    int delNoticePics(List<Integer> inoticePic);

    //------------------------ 알림장 글 삭제 ------------------------
    int delNotice(int inotice);
    //------------------------ 알림장 삭제 댓글, 사진 ------------------------
    int delAllNotice(int inotice);

    //------------------------ 학부모 유저 시점 원아 알림장 전체 조회 ------------------------
    List<SelAllNoticeVo> selAllNoticeBoardPar(SelAllNoticeDto dto);
    //------------------------ 학부모 유저 시점 원아 알림장 전체 개수 조회 ------------------------
    int selNoticeParCnt(SelAllNoticeDto dto);
    //------------------------ 선생님 유저 시점 원아 알림장 전체 조회 ------------------------
    List<SelAllNoticeVo> selAllNoticeBoardTea(SelAllNoticeDto dto);
    //------------------------ 선생님 유저 시점 원아 알림장 전체 개수 조회 ------------------------
    int selNoticeTeaCnt(SelAllNoticeDto dto);
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
    //-------------------------------- 알림장 작성 시 원아 태그에 쓰일 모든 원아 정보 --------------------------------
    List<SelTagKids> selFromKids();

}
