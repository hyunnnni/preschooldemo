package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "관리자 입장에서 학부모관리 페이지 조회에 필요한 데이터")
public class SelParManagementDto {
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;
    @Schema(title = "조회 시 선택하는 반 전체 조회 시 값 필요없음")
    private int iclass;

    @JsonIgnore
    private int ilevel;
    @Schema(title = "현재 재원중인 원아의 가족 계정만 조회하기 위한 값")
    @JsonIgnore
    private int prIsDel= Const.ZERO;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;

    public void setPage(int page) {
        this.rowCount = Const.PAGE_ROWCOUNT;
        this.startIdx = (page-1) * rowCount;
    }
}
