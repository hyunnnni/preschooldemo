package com.preschool.preschooldemo.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "로그인한 유저 토큰")
public class UserFirebaseTokenPatchDto {
    @Schema(title = "로그인유저PK")
    private int iuser;
    @Schema(title = "토큰")
    private String firebaseToken;
}