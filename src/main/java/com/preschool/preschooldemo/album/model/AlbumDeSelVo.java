package com.preschool.preschooldemo.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AlbumDeSelVo {
    @Schema(title = "활동 앨범 글 제목")
    private String albumTitle;
    @Schema(title = "활동 앨범 글 내용")
    private String albumContents;
    @Schema(title = "활동 앨범 사진들")
    private List<AlbumPicsVo> albumPic;
    @JsonIgnore
    private int ialbum;
}
