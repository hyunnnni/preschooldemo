package com.preschool.preschooldemo.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "아이디 중복 확인")
public class CodeCorrect {
    @Schema(title = "회원가입 가능 여부")
    private int isValid;
    @Schema(title = "메세지")
    private String message;

}
