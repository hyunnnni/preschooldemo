package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Parent수정 전 정보 Select")
public class TeacherParentBeforInfoVo {
    @Schema(title = "부모PK")
    private int iparent;
    @Schema(title = "부모이름")
    private String parentNm;
    @Schema(title = "휴대폰번호")
    private String phoneNb;
    @Schema(title = "아이디")
    private String uid;
    @Schema(title = "이메일")
    private String prEmail;

}
