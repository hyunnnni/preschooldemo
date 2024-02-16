package com.preschool.preschooldemo.album.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlbumSelVo {
    private int ialbum;
    @Schema(title = "활동 앨범 제목")
    private String albumTitle;
    @Schema(title = "활동 앨범 보기 사진")
    private String albumPic;
}
