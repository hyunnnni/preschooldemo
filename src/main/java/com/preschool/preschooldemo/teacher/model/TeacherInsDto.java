package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(title = "3차 선생님 회원가입")
public class TeacherInsDto {

    @JsonIgnore
    private int iteacher;

    @Schema(title = "반 PK")
    private int iclass;

    @Schema(title = "선생님 이름")
    @Pattern(regexp = "^[가-힣|a-z|A-Z]*$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String teacherNm;

    @Schema(title = "선생님 ID")
    @Pattern(regexp = "^[a-z|A-Z|0-9]*$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String teacherUid;

    @Schema(title = "선생님 PW")
    @Pattern(regexp = "^[a-z|A-Z|0-9|_-~!@#$%^&]*$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String teacherUpw;

    @Schema(title = "선생님 프로필 사진")
    @JsonIgnore
    private String teacherProfile;

    @Schema(title = "선생님 소개글")
    @NotBlank
    private String teacherIntroduce;

    @Schema(title ="선생님 이메일")
    @NotBlank
    private String tcEmail;

    @Schema(title ="권한")
    @JsonIgnore
    private String role;
}
