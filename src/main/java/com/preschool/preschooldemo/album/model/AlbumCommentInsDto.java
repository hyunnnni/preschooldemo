package com.preschool.preschooldemo.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AlbumCommentInsDto {
    @JsonIgnore
    private int ialbumComment;

    @Positive(message = "잘못된 값입니다")
    @Schema(title = "활동 앨범 PK")
    private int ialbum;

    @NotNull(message = "댓글을 입력해주세요")
    @Schema(title = "활동 앨범 댓글")
    private String albumComment;

    @JsonIgnore
    @Schema(title = "선생님 PK")
    private int iteacher;

    @JsonIgnore
    @Schema(title = "부모님 PK")
    private int iparent;

}
