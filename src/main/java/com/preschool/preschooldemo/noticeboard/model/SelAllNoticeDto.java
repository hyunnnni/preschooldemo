package com.preschool.preschooldemo.noticeboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@Schema(title = "알림장 조회 접근 유저에 따라 다르게 조회에 필요한 데이터")
public class SelAllNoticeDto {
    @Schema(title = "페이징 시 필요한 데이터")
    private int page;

    @Schema(title = "이 페이지에 접근하는 유저의 등급 PK")
    @Range(min = 1, max = 3, message = "해당 페이지에 접근할 권한이 없습니다.")
    private int ilevel;

    @Schema(title = "학부모 유저가 접근 시 본인과 연결된 원아 PK / 연결된 모든 원아 조회 시 = 0")
    private int ikid;

    @Schema(title = "선생님 유저가 접근 시 원하는 원아들 반 PK /전체 조회 시 = 0")
    @Range(min = 0, max = 3, message = "해당 반으로 검색되는 아이가 없습니다.")
    private int iclass;

    @Schema(title = "해당 원아의 알림장 업로드 년도/ 전체 조회 시 = null")
    private String year;

    @Schema(title = "이 페이지에 접근한 유저(선생님 OR 학부모)의 PK")
    @Min(value = 1, message = "로그인한 유저를 알 수 없습니다")
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
