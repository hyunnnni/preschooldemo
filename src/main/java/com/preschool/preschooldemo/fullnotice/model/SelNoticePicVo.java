package com.preschool.preschooldemo.fullnotice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "유치원 소식 사진불러오기")
public class SelNoticePicVo {
    @JsonIgnore
    private int ilevel;
    @Schema(title = "유치원 소식 사진")
    private List<String> pics = new ArrayList<>();
    @Schema(title = "유치원 소식PK")
    private int iFullNotice;
}
