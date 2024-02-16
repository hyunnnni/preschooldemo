package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "관리자 입장에서 원아관리 페이지 조회 응답 데이터 반/재원상태로도 나눌 수 있음")
public class SelKidManagement {
    @Schema(title = "변경을 원하는 원아PK")
    private int ikid;
    @Schema(title = "카테고리 반PK")
    private int iclass;
    @Schema(title = "원아 이름")
    private String kidNm;
    @Schema(title = "원아 프로필 사진")
    private String profile;
}
