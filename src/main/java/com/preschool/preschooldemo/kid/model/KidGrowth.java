package com.preschool.preschooldemo.kid.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(title = "원아 발달사항")
public class KidGrowth {
    @Schema(title = "키")
    @Positive(message = "값을 확인해주세요")
    private int height;
    @Schema(title = "체중")
    @Positive(message = "값을 확인해주세요")
    private int weight;
    @Schema(title = "측정일")
    @Pattern(regexp = "^([12]\\d{3})-(0[1-9]|1[012])-(0[0-9]|[12][0-9]|3[01])$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String bodyDate;
    @Schema(title = "칭찬카드")
    @Range(min = 1,max = 10, message = "값을 확인해주세요")
    private int growth;
    @Schema(title = "입력일")
    @Pattern(regexp = "^([12]\\d{3})-(0[1-9]|1[012])-(0[0-9]|[12][0-9]|3[01])$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String growthDate;
    @Schema(title = "발달사항 메모")
    private String growthMemo;
    @Schema(title = "신체 분기")
    private int bodyQuarterly;
    @Schema(title = "발달사항 분기")
    private int growthQuarterly;
}
