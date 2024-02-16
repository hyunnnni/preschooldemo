package com.preschool.preschooldemo.kid.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "원아 부모 조회")
public class KidParent {
    @Schema(title = "부모 pk")
    private int iparent;
    @Schema(title = "아이디", type = "String")
    private String uid;
    @Schema(title = "부모 이름", type = "String")
    private String parentNm;
    @Schema(title = "부모 전화번호", type = "String")
    private String phoneNb;
    @Schema(title = "관계")
    private int irelation;
}
