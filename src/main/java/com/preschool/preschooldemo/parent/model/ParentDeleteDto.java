package com.preschool.preschooldemo.parent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "회원 탈퇴")
public class ParentDeleteDto {
    @JsonIgnore
    private int iparent;
}
