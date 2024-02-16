package com.preschool.preschooldemo.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(title = "원아 발달사항 수정")
public class KidDetailUpdDto {
    @Schema(title = "원아 pk")
    @Positive(message = "원아 번호를 입력해주세요")
    private int ikid;
    @Schema(title = "키")
    @PositiveOrZero(message = "값을 확인해주세요")
    private int height;
    @Schema(title = "체중")
    @PositiveOrZero(message = "값을 확인해주세요")
    private int weight;
    @Schema(title = "칭찬카드")
    private int growth;
    @Schema(title = "입력일")
    @Pattern(regexp = "^([12]\\d{3})-(0[1-9]|1[012])-(0[0-9]|[12][0-9]|3[01])$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String growthDate;
    @Schema(title = "발달사항 메모")
    private String growthMemo;
    @Schema(title = "측정일")
    @Pattern(regexp = "^([12]\\d{3})-(0[1-9]|1[012])-(0[0-9]|[12][0-9]|3[01])$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String bodyDate;
    @JsonIgnore
    private int bodyQuarterly;
    @JsonIgnore
    private int growthQuarterly;
}
