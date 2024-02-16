package com.preschool.preschooldemo.notice.model;

import lombok.Data;

import java.util.List;

@Data
public class AllNoticeVo {
    private List<SelAllNoticeVo> list;
    private int noticeCnt;
}
