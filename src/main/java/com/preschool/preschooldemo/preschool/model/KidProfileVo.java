package com.preschool.preschooldemo.preschool.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "원아 정보")
public class KidProfileVo {
    @Schema(title = "원아 PK")
    private int ikid;
    @Schema(title = "반 PK")
    private int iclass;
    @Schema(title = "원아 이름")
    private String kidNm;
    @Schema(title = "원아 사진")
    private String profile;
}
