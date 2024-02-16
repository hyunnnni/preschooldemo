package com.preschool.preschooldemo.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "원아 프로필 정보 조회")
public class KidProfileVo {
    @JsonIgnore
    private int result;
    @Schema(title = "원아 이름", type = "String")
    private String kidNm;
    @Schema(title = "반")
    private int iclass;
    @Schema(title = "성별")
    private int gender;
    @Schema(title = "프로필")
    private String profile;
    @Schema(title = "출생일")
    private String birth;
    @Schema(title = "주소")
    private String address;
    @Schema(title = "발달사항")
    List<KidGrowth> growths = new ArrayList<>();
    @Schema(title = "부모님")
    List<KidParent> parents = new ArrayList<>();
    @Schema(title = "식별코드")
    private String code;
    @Schema(title = "메모", type = "String")
    private String memo;
    @Schema(title = "비상 연락자", type = "String")
    private String emerNm;
    @Schema(title = "비상 연락망", type = "String")
    private String emerNb;

}
