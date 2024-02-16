package com.preschool.preschooldemo.fullnotice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(title = "유치원 소식 전체 조회")
public class SelFullNoticeDto {
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;

    @Schema(title = "이 페이지에 접근하는 유저의 등급 PK")
    @Range(min = 1, max = 3, message = "해당 페이지에 접근할 권한이 없습니다.")
    private int ilevel;

    @Schema(title = "검색 기능")
    private String search;

    @JsonIgnore
    private int starIdx;
    @JsonIgnore
    private int row;


    public void setPage(int page) {
        this.row = Const.NOTICE_COUNT_PER_PAGE;
        this.starIdx = (page - 1) * row;
    }
}

