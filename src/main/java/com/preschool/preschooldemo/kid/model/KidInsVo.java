package com.preschool.preschooldemo.kid.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "원아 코드 출력")
public class KidInsVo {
    @Schema(title = "원아 PK")
    private int ikid;
    @Schema(title = "코드")
    private String code;
}
