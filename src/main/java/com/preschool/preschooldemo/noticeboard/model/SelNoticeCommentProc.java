package com.preschool.preschooldemo.noticeboard.model;

import lombok.Data;

@Data
public class SelNoticeCommentProc {
    private int inoticeComment;
    private String noticeComment;
    private String writerIuser;
    private String writerName;
    private String createdAt;
}
