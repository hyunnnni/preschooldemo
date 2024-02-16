package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "선생님 수정 시 응답할 데이터")
public class TeacherEditVo {
    @Schema(title = "선생님의 PK")
    private int iteacher;
    @Schema(title = "선생님의 등급")
    private int ilevel;
    @Schema(title = "선생님 담당 반")
    private int iclass;
    @Schema(title = "선생님 이름")
    private String teacherNm;
    @Schema(title = "선생님 아이디")
    private String teacherUid;
    @Schema(title = "선생님 비밀번호")
    private String teacherUpw;
    @Schema(title = "선생님 소개글")
    private String teacherIntroduce;
    @Schema(title = "선생님 권환")
    private String tcRole;
    @Schema(title = "선생님 사진")
    private String teacherProfile;
    @Schema(title = "선생님 이메일")
    private String tcEmail;
}
