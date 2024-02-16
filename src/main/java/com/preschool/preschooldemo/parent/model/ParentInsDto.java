package com.preschool.preschooldemo.parent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "부모 정보 입력", title = "식별코드 인증 후 회원가입")
public class ParentInsDto {
    @Schema(title = "앞에서 받은 아이 pk(자동매칭을 위한 것)")
    private int ikid;
    @JsonIgnore
    private int iparent;

    @Schema(title = "아이와의 관계")
    private int irelation;
    @Schema(title = "아이디 중복체크")
    private int isValid; // 중복체크가 되었는지 확인 (1: 문제없음, 나머지: 문제있음)

    @Schema(title = "부모이름")
    @Pattern(regexp = "^[가-힣|a-z|A-Z]*$",
    message = "형식이 맞지 않습니다 확인해주세요")
    private String parentNm;

    @Schema(title = "아이디")
    @Pattern(regexp = "^[a-z|A-Z|0-9]*$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String uid;

    @Schema(title = "비밀번호")
    @Pattern(regexp = "^[a-z|A-Z|0-9|_-~!@#$%^&]*$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String upw;

    @Schema(title = "휴대폰번호")
    @Pattern(regexp = "^01[0-1|6-9]{1}[\\d]{7,8}$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String phoneNb;

    @Schema(title = "이메일")
    @Pattern(regexp = "^[a-z|A-Z|0-9|_|-]+@([a-z|0-9]{3,}\\.[a-z]{2,}|[a-z|0-9]{3,}\\.[a-z]{2,}\\.[a-z]{2,})$",
    message = "이메일 양식을 확인해주세요")
    private String prEmail;

    @JsonIgnore
    private String code;

}
