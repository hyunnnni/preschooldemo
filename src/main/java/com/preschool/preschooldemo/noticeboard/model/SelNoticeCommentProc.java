package com.preschool.preschooldemo.noticeboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "알림장 상세 조회 댓글 정보")
public class SelNoticeCommentProc {
    @Schema(title = "알림장 댓글 pk")
    private int inoticeComment;
    @Schema(title = "알림장 댓글 내용")
    private String noticeComment;
    @Schema(title = "작성자의 등급 pk 구별")
    private int ilevel;
    @Schema(title = "작성자의 pk")
    private String writerIuser;
    @Schema(title = "작성자의 이름")
    private String writerName;
    @Schema(title = "알림장 댓글 작성일")
    private String createdAt;
}
