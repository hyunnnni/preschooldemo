package com.preschool.preschooldemo.album.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AlbumDelCommentDto {
    @Schema(title = "앨범 댓글 PK")
    private int ialbumComment;
    @Schema(title = "앨범 PK")
    private int ialbum;
    @Schema(title = "선생님 PK")
    private int iteacher;
    @Schema(title = "부모님 PK")
    private int iparent;
}
