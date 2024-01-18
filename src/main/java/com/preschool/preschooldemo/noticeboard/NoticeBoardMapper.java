package com.preschool.preschooldemo.noticeboard;

import com.preschool.preschooldemo.noticeboard.model.SelAllNoticeDto;
import com.preschool.preschooldemo.noticeboard.model.SelAllNoticeVo;
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
}
