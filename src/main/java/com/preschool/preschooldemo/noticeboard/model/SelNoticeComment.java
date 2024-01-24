package com.preschool.preschooldemo.noticeboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "알림장 댓글 정보 데이터")
public class SelNoticeComment {
    private int inoticeComment;
    private String noticeComment;
    private int iteacher;
    private int iparent;
    private String createdAt;
}
