package com.preschool.preschooldemo.album.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "앨범 조회")
public class AllAlbumSelVo {
    @Schema(title = "앨범 개수")
    private int albumCnt;
    @Schema(title = "앨범 리스트")
    private List<AlbumSelVo> list;
}
