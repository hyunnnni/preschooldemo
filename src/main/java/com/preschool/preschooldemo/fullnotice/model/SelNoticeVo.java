package com.preschool.preschooldemo.fullnotice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "유치원 소식 상세 조회")
public class SelNoticeVo {
    @Schema(title = "유치원 소식 제목")
    private String fullTitle;
    @Schema(title = "유치원 소식 내용")
    private String fullContents;
    @Schema(title = "유치원 소식 작성자")
    private String writer;
    @Schema(title = "유치원 소식 등록일")
    private String createdAt;
    @Schema(title = "유치원 소식 사진과 PK")
    private List<SelNoticePics> pics;
}
