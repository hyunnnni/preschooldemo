package com.preschool.preschooldemo.album.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlbumPicsInsDto {
    @Schema(title = "활동 앨범 PK")
    private int ialbum;
    @Schema(title = "활동 앨범에 첨부한 사진들")
    private List<String> albumPic = new ArrayList<>();
}
