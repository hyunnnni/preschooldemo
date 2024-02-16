package com.preschool.preschooldemo.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class AlbumUpdDto {
    @JsonIgnore
    private int ilevel;

    @Schema(title = "선생님 PK")
    @Positive(message = "잘못된 값입니다")
    private int iteacher;

    @Schema(title = "활동 앨범 제목")
    @NotBlank(message = "제목을 입력해주세요")
    private String albumTitle;

    @Schema(title = "활동 앨범 내용")
    @NotBlank(message = "내용을 입력해주세요")
    private String albumContents;

    @Schema(title = "활동 앨범 PK")
    @Positive(message = "잘못된 값입니다")
    private int ialbum;

    @Schema(title = "삭제된 사진이 있다면 그 사진의 pk들")
    private List<Integer> delPics;

    @JsonIgnore
    @Schema(title = "활동 앨범에 첨부한 사진들")
    private List<String> albumPic;
}
