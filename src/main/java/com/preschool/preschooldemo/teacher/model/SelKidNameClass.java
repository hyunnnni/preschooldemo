package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "학부모 관리 페이지 조회 시 해당 학부모의 원아 이름/반 응답 데이터")
public class SelKidNameClass {
    @Schema(title = "원아 이름")
    private String kidNm;
    @Schema(title = "원아 반")
    private int iclass;
}
