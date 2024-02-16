package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TeacherPatchDto {
    @Schema(title = "선생님 PK")
    @Positive(message = "잘못된 값입니다")
    private int iteacher;

    @JsonIgnore
    private int loginIlevel;

    @Schema(title = "선생님 이름")
    @Pattern(regexp = "^[가-힣|a-z|A-Z]*$",
            message = "형식이 맞지 않습니다. 확인해주세요.")
    private String teacherNm;

    @Schema(title = "해당 선생님의 등급")
    private int ilevel;

    @Schema(title = "반 PK")
    @Positive(message = "잘못된 값입니다.")
    private int iclass;

    @Schema(title = "선생님 ID")
    @Pattern(regexp = "^[가-힣|a-z|A-Z]*$",
            message = "형식이 맞지 않습니다. 확인해주세요.")
    private String teacherUid;

    @Schema(title = "선생님 PW")
    @Pattern(regexp = "^[a-z|A-Z|0-9|_-~!@#$%^&]*$",
            message = "형식이 맞지 않습니다. 확인해주세요.")
    private String teacherUpw;

    @Schema(title = "선생님 이메일")
    @Pattern(regexp = "^[a-z|A-Z|0-9|_|-]+@([a-z|0-9]{3,}\\.[a-z]{2,}|[a-z|0-9]{3,}\\.[a-z]{2,}\\.[a-z]{2,})$",
            message = "이메일 양식을 확인해주세요.")
    private String tcEmail;

    @Schema(title = "선생님 프로필 사진")
    @JsonIgnore
    private String teacherProfile;

    @Schema(title = "선생님 소개글")
    @NotBlank(message = "소개글을 입력해주세요.")
    private String teacherIntroduce;

    @Schema(title = "선생님 권한")
    private String tcRole;
}
