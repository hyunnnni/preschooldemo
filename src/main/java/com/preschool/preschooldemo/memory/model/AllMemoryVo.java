package com.preschool.preschooldemo.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AllMemoryVo {
    List<AllSelMemoryVo> list;
    @Schema(title = "추억앨범 총 개수")
    private int imemoryCnt;
}
