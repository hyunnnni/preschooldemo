package com.preschool.preschooldemo.memory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "추억앨범 조회")
public class AllSelMemoryDto {
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;
    @Schema(title = "반PK")
    private int iclass;
    @Schema(title = "원아PK")
    private int ikid;
    @Schema(title = "검색기능")
    private String search;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;

    public void setPage(int page) {
        this.rowCount = Const.PAGE_ROWCOUNT;
        this.startIdx = (page-1)*rowCount;
    }



}
