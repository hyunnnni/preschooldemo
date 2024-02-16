package com.preschool.preschooldemo.parent.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "t_parent_kid table에 아이&부모PK Insert")
public class ParentKid {
    @JsonIgnore
    private int ikid;
    @Schema(title = "부모PK")
    private int iparent;

    @Schema(title = "아이 정보")
    private List<SelKid> kidList;


    private int result;
    private String firebaseToken;
    private String accessToken;
}
