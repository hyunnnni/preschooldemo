package com.preschool.preschooldemo.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "부모 로그인")
public class ParentEntity {
    @Schema(title = "부모 PK")
    private int iparent;
    @Schema(title = "아이와의 관계")
    private int irelation;
    private String role;
    @Schema(title = "등급")
    private int ilevel;
    @Schema(title = "로그인")
    private String uid;
    @Schema(title = "비밀번호")
    private String upw;
    @Schema(title = "부모이름")
    private String parentNm;
    @Schema(title = "휴대폰번호")
    private String phoneNb;
    @Schema(title = "이메일")
    private String prEmail;
    @Schema(title = "토큰")
    private String firebaseToken;
    @Schema(title = "회원가입 한 날짜")
    private String createdAt;
    @Schema(title = "아이 PK")
    private int ikid;
    @Schema(title = "계정삭제여부")
    private int prIsDel;
}
