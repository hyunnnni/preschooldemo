package com.preschool.preschooldemo.fullnotice;

import com.preschool.preschooldemo.fullnotice.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FullNoticeMapper {

    //-------------------------------- 게시판 전체 조회 시 상단 공지 --------------------------------
    List<SelFullNoticeVo> getFullNoticeFix(String search);
    //-------------------------------- 게시판 전체 조회 일반 공지 --------------------------------
    List<SelFullNoticeVo> getFullNoticelist(SelFullNoticeDto dto);
    //--------------------------------- 게시판 전체 조회 개수 -------------------------------------
    int selNoticeCnt(String search);
    int selNoticeFixCnt(String search);
    //-------------------------------- 게시판 상세 조회 사진 --------------------------------
    List<SelNoticePics> selNoticePic(int iFullNotice);
    //-------------------------------- 게시판 상세 조회 글 --------------------------------
    SelNoticeVo getNotice(int iFullNotice);

    //-------------------------------- 유치원 소식 작성 --------------------------------
    int insFullNotice(InsFullNoticeDto dto);
    //-------------------------------- 유치원 소식 사진 작성 --------------------------------
    int insFullNoticePics(InsFullPicsDto dto);
    //-------------------------------- 유치원 소식 작성자 pk 조회 --------------------------------
    Integer selFullNoticeWriter(int ifullNotice);
    //-------------------------------- 유치원 소식 삭제 --------------------------------
    int delFullNotice(DelFullNoticeDto dto);
    //-------------------------------- 유치원 소식 사진 삭제 --------------------------------
    int delFullNoticePics(DelFullNoticeDto dto);
    //-------------------------------- 유치원 소식 수정 --------------------------------
    int putFullNotice(UpdFullNoticeDto dto);
    //-------------------------------- 유치원 소식 업데이트 시 사진 삭제 --------------------------------
    int delUpdFullNoticePics(List<Integer> delPics);
    //-------------------------------- 유치원 소식 사진 개수 조회 --------------------------------
    Integer selFullNoticePics(int ifullNotice);
    //-------------------------------- 유치원 소식 상단 고정 기능 조회 --------------------------------
    Integer selNoticeFix(int ifullNotice);
    //-------------------------------- 유치원 소식 수정 시 불러오기 --------------------------------
    SelFullNoticeUpdVo selFullNoticeUpd(int ifullNotice);
    //-------------------------------- 유치원 소식 수정 시 사진 불러오기  --------------------------------
    List<String> selFullNoticeUpdPics(int ifullNotice);
}
