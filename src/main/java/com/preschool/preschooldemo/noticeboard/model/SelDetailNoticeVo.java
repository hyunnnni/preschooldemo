package com.preschool.preschooldemo.noticeboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "알림장 상세 조회 응답 데이터")
public class SelDetailNoticeVo {
    @Schema(title = "알림장 PK")
    private int inotice;
    @Schema(title = "알림장 제목")
    private String noticeTitle;
    @Schema(title = "알림장 내용")
    private String noticeContents;
    @Schema(title = "알림장 사진")
    private List<String> pics;
    @Schema(title = "알림장 댓글")
    private List<SelNoticeCommentProc> comments;
    @Schema(title = "알림장 업로드일")
    private String createdAt;
    @Schema(title = "알림장을 받는 아이 이름")
    private String kidNm;
    @Schema(title = "알림장을 받는 아이 반")
    private int iclass;
}
