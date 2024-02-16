package com.preschool.preschooldemo.notice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "알림장 조회 응답데이터")
public class SelAllNoticeVo {
    @Schema(title = "알림장 PK")
    private int inotice;
    @Schema(title = "알림장 제목")
    private String noticeTitle;
    @Schema(title = "알림장 내용")
    private String noticeContents;
    @Schema(title = "알림장을 받는 아이 이름")
    private String kidNm;
    @Schema(title = "알림장을 받는 아이 반")
    private int iclass;
    @Schema(title = "해당 알림장에 사진 포함 1: 유/0 : 무")
    private int picCheck;
    @Schema(title = "알림장 업로드일")
    private String createdAt;
}
