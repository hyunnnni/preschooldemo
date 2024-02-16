package com.preschool.preschooldemo.fullnotice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "유치원 소식 작성 중 사진 저장")
public class InsFullPicsDto {
    @Schema(title = "유치원 소식 사진")
    private List<String> fullPic = new ArrayList<>();
    @Schema(title = "유치원 소식 pk")
    private int ifullNotice;
}
