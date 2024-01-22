package com.preschool.preschooldemo.fullnotice;

import com.preschool.preschooldemo.fullnotice.model.DelFullNoticeDto;
import com.preschool.preschooldemo.fullnotice.model.InsFullNoticeDto;
import com.preschool.preschooldemo.fullnotice.model.InsFullPicsDto;
import com.preschool.preschooldemo.fullnotice.model.UpdFullNoticeDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FullNoticeMapper {
//-------------------------------- 유치원 소식 작성 --------------------------------
    int postFullNotice(InsFullNoticeDto dto);
//-------------------------------- 유치원 소식 사진 작성 --------------------------------
    int postFullNoticePics(InsFullPicsDto dto);
//-------------------------------- 유치원 소식 삭제 (선생님 ) --------------------------------
    int delFullNotice(DelFullNoticeDto dto);
//-------------------------------- 유치원 소식 삭제 (선생님 ) --------------------------------
    int delFullNoticeBoss(int ifullNotice);
//-------------------------------- 유치원 소식 상단 고정 기능 조회 --------------------------------
    Integer selNoticeFix(int ifullNotice);
//-------------------------------- 유치원 소식 수정 --------------------------------
    int putFullNotice(UpdFullNoticeDto dto);
//-------------------------------- 유치원 소식 사진 삭제 --------------------------------
    int delFullNoticePics(int ifullNotice);
//-------------------------------- 유치원 소식 사진 개수 조회 --------------------------------
    Integer selFullNoticePics(int ifullNotice);
}
