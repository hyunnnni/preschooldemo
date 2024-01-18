package com.preschool.preschooldemo.noticeboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "알림장 조회 접근 유저에 따라 다르게 조회에 필요한 데이터")
public class SelAllNoticeDto {
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;
    @Schema(title = "이 페이지에 접근하는 유저의 등급 PK")
    private int ilevel;
    @Schema(title = "학부모 유저가 접근 시 본인과 연결된 원아 PK")
    private int ikid;
    @Schema(title = "선생님 유저가 접근 시 원하는 원아들 반 PK 전체 조회 시 = 0")
    private int iclass;
    @Schema(title = "해당 원아의 알림장 업로드 년도")
    private String year;
    @Schema(title = "이 페이지에 접근한 유저(선생님 OR 학부모)의 PK")
    private int loginedIuser;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;

    public void setPage(int page) {
        this.rowCount = Const.PAGE_ROWCOUNT;
        this.startIdx = (page-1)*rowCount;
    }
}
