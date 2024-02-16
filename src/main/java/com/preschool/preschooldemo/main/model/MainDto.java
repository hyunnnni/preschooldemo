package com.preschool.preschooldemo.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.preschool.preschooldemo.common.utils.Const.MAIN_ROW_COUNT;

@Data
@Schema(title = "메인화면 앨범 조회")
public class MainDto {
    @JsonIgnore
    @Schema(title = "페이징 처리 시작")
    private int startIdx = Const.ZERO;
    @JsonIgnore
    @Schema(title = "페이징 처리 끝")
    private int rowCount = MAIN_ROW_COUNT;
}
