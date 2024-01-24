package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "관리자 입장에서 학부모계정과 원아 연결 끊기")
public class DelDisconnectDto {
    @Schema(title = "연결을 끊을 학부모PK")
    private int iparent;
    @Schema(title = "연결을 끊을 원아PK")
    private int ikid;
    @JsonIgnore
    private int ilevel;
}
