package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "선생님 로그인 시 응답 데이터")
public class TeacherEntity {
    private int iteacher;
    private int ilevel;
    private String role;
    private int iclass;
    private String teacherUid;
    private String teacherUpw;
    private String teacherNm;
    private String tcEmail;
    private String teacherIntroduce;
    private String teacherProfile;
    private String accessToken;
    private String firebaseToken;
    private String createdAt;
    private int tcIsDel;
}
