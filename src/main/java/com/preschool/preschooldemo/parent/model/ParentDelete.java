package com.preschool.preschooldemo.parent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "Parent isDel 처리")
public class ParentDelete {
    @Schema(title = "부모PK")
    private int iparent;
    @Schema(title = "탈퇴여부")
    private int isdel;
}
