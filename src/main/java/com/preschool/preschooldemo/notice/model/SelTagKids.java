package com.preschool.preschooldemo.notice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "알림장 작성 시 태그할 원아들의 정보를 가진 응답값")
public class SelTagKids {
    @Schema(title = "원아 PK")
    private int ikid;
    @Schema(title = "반 PK")
    private int iclass;
    @Schema(title = "원아 이름")
    private String kidNm;
}
