package com.preschool.preschooldemo.noticeboard.model;

import lombok.Data;

@Data
public class SelNoticeComment {
    private int inoticeComment;
    private String noticeComment;
    private int iteacher;
    private int iparent;
    private String createdAt;
}
