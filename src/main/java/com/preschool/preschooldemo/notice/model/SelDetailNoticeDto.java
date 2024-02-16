package com.preschool.preschooldemo.notice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "알림장 상세 조회에 필요한 데이터")
public class SelDetailNoticeDto {
    @Schema(title = "알림장 pk")
    private int inotice;
    @JsonIgnore
    private int ilevel;

}
