package com.preschool.preschooldemo.noticeboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
@Data
@Schema(title = "알림장 상세 조회에 필요한 데이터")
public class SelDetailNoticeDto {
    @Schema(title = "알림장 pk")
    @Min(value = 0, message = "해당 알림장의 pk가 잘못됨")
    private int inotice;
    @Schema(title = "접근하는 유저의 등급 pk")
    @Range(min = 1, max = 3,message = "해당 페이지에 접근할 권한 없음")
    private int ilevel;

}
