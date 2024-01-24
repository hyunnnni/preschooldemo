package com.preschool.preschooldemo.fullnotice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "유치원 소식 수정 시 불러오기에 필요한 요청 데이터")
public class SelFullNoticeUpdDto {
    @Schema(title = "유치원 소식 pk")
    private int ifullNotice;
    @JsonIgnore
    private int ilevel;
}
