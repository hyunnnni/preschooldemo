package com.preschool.preschooldemo.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "코드인증 후 아이정보")
public class CodeVo {
    @Schema(title = "코드")
    private String  code;
    @Schema(title = "아이 PK")
    private int ikid;
    @Schema(title = "아이 이름")
    private String kidNm;
    @Schema(title = "반 PK")
    private int iclass;



}
