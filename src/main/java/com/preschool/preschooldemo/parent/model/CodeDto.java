package com.preschool.preschooldemo.parent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(title = "코드 입력")
public class CodeDto {
    @Schema(title = "코드")
    @NotBlank
    private String code;
    @JsonIgnore
    private int iparent;

}
