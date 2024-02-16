package com.preschool.preschooldemo.parent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Parent수정 전 정보")
public class ParentBeforinfoDto {
    @JsonIgnore
    private int iparent;
    @Schema(title = "등급PK")
    private int ilevel;
}
