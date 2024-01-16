package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(title = "관리자 입장에서 원아관리 페이지 조회 응답 데이터 반/재원상태로도 나눌 수 있음")
public class SelParManagementVo {
    @Schema(title = "학부모 PK")
    private int iparent;
    @Schema(title = "학부모 이름")
    private int parentNm;
    @Schema(title = "학부모 아이디")
    private int uid;
    @Schema(title = "학부모 전화번호")
    private int phoneNb;
    @Schema(title = "원아 이름")
    private String kidNm;
    @Schema(title = "반PK")
    private String icalss;
    @Schema(title = "실패 응답값")
    private int result;

}
