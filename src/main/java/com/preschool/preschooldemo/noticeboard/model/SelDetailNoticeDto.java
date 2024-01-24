package com.preschool.preschooldemo.noticeboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
@Data
@Schema(title = "알림장 상세 조회에 필요한 데이터")
public class SelDetailNoticeDto {
    @Schema(title = "알림장 pk")
    private int inotice;
    @JsonIgnore
    private int ilevel;

}
