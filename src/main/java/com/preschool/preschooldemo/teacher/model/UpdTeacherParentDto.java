package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(title = "선생님이 부모 정보수정")
public class UpdTeacherParentDto {
    @Schema(title = "부모PK")
    private int iparent;
    @Schema(title = "부모이름")
    @Pattern(regexp = "^[가-힣|a-z|A-Z]*$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String parentNm;
    @Schema(title = "휴대폰번호")
    @Pattern(regexp = "^01[0-1|6-9]{1}[\\d]{7,8}$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String phoneNb;
    @Schema(title = "이메일")
    @Pattern(regexp = "^[a-z|A-Z|0-9|_|-]+@([a-z|0-9]{3,}\\.[a-z]{2,}|[a-z|0-9]{3,}\\.[a-z]{2,}\\.[a-z]{2,})$")
    private String prEmail;
    @Schema(title = "비밀번호")
    @Pattern(regexp = "^[a-z|A-Z|0-9|_-~!@#$%^&]*$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String upw;

}
