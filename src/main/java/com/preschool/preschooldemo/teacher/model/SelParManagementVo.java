package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "관리자 입장에서 원아관리 페이지 조회 응답 데이터 반/재원상태로도 나눌 수 있음")
public class SelParManagementVo {
    @Schema(title = "총 개수")
    private Integer totalCnt;
    @Schema(title = "학부모 페이지")
    private List<SelParManagement> parentPage;
}
