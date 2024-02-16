package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SelAllTeacherDto {
    @Schema(title = "반PK")
    private int iclass;
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;

    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;

    public void setPage(int page) {
        this.rowCount = Const.ROW_COUNT;
        this.startIdx = (page - 1) * rowCount;
        this.page = page;

    }
}
