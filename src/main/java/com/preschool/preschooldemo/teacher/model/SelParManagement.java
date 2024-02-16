package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "관리자 입장에서 원아관리 페이지 조회 응답 데이터 반/재원상태로도 나눌 수 있음")
public class SelParManagement {
    @Schema(title = "학부모 PK")
    private int iparent;
    @Schema(title = "학부모 이름")
    private String parentNm;
    @Schema(title = "학부모 아이디")
    private String uid;
    @Schema(title = "학부모 전화번호")
    private String phoneNb;
    @Schema(title = "해당 학부모의 원아 이름과 반 리스트")
    private List<SelKidNameClass> kids;
}
