package com.preschool.preschooldemo.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "원아 마이페이지 수정 시 조회")
public class KidDetailEditVo {
    @JsonIgnore
    private int result;
    @Schema(title = "이름", type = "String")
    private String kidNm;
    @Schema(title = "반", type = "String")
    private int iclass;
    @Schema(title = "성별", type = "String")
    private int gender;
    @Schema(title = "출생일", type = "String")
    private String birth;
    @Schema(title = "발달사항", type = "List")
    List<KidGrowth> growths = new ArrayList<>();


}
