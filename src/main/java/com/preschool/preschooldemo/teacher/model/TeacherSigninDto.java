package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "로그인")
public class TeacherSigninDto {
    @JsonIgnore
    private int iteacher;
    @Schema(title = "아이디")
    private String teacherUid;
    @Schema(title = "비밀번호")
    private String teacherUpw;



}
