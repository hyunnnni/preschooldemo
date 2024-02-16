package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TeacherRankDto {
    @JsonIgnore
    private int iteacher;
    @Schema(title = "직급 PK")
    private int ilevel;
}
