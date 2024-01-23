package com.preschool.preschooldemo.fullnotice.model;

import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(title = "유치원 소식 삭제에 필요한 데이터")
public class DelFullNoticeDto {
    @Schema(title = "삭제를 원하는 유저(관리자만)의 pk")
    private int iteacher;
    @Schema(title = "삭제 대상 유치원 소식")
    private int ifullNotice;
    @Schema(title = "유저의 등급 pk")
    private int ilevel;
}
