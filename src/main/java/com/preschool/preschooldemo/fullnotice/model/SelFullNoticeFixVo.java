package com.preschool.preschooldemo.fullnotice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "공지사항")
public class SelFullNoticeFixVo {
    @Schema(title = "유치원 소식 공지")
    private int fullNoticeFix;
}
