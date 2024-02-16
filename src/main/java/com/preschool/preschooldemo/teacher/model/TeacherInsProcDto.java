package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(title = "선생님 회원가입 시 사용할 데이터 3차 사용")
public class TeacherInsProcDto {
    @Schema(title = "선생님 이름")
    private int teacherNm;
    @JsonIgnore
    private int ilevel;
    @Schema(title = "반 PK")
    private int iclass;
    @Schema(title = "선생님 ID")
    private String teacherUid;
    @Schema(title = "선생님 PW")
    private String teacherUpw;
    @Schema(title = "선생님 프로필 사진")
    private String teacherProfile;
    @Schema(title = "선생님 소개글")
    private String teacherIntroduce;
    @Schema(title = "유치원 식별코드")
    private String preCode;
}
