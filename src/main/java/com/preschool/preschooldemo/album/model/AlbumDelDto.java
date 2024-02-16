package com.preschool.preschooldemo.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlbumDelDto {
    @Schema(title = "활동 앨범 PK")
    private int ialbum;
    @Schema(title = "선생님 PK")
    private int iteacher;
    @JsonIgnore
    private int ilevel;
}
