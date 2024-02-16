package com.preschool.preschooldemo.main.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "유치원 소식 전체 조회")
public class SelFullNoticeVo {
    @Schema(title = "유치원 소식 pk")
    private String ifullNotice;
    @Schema(title = "유치원 소식 제목")
    private String fullTitle;
    @Schema(title = "유치원 소식 작성자")
    private String writer;
    @Schema(title = "유치원 소식 고정공지")
    private int fullNoticeFix;
    @Schema(title = "유치원 소식 등록일")
    private String createdAt;
}
