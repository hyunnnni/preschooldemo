package com.preschool.preschooldemo.album.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AlbumInsDto {
    @Schema(title = "선생님 PK")
    @Positive(message = "잘못된 값입니다")
    private int iteacher;

    @Schema(title = "활동 앨범 제목")
    @NotNull(message = "제목을 입력해주세요")
    private String albumTitle;

    @Schema(title = "활동 앨범 내용")
    @NotNull(message = "내용을 입력해주세요")
    private String albumContents;

    @JsonIgnore
    private List<MultipartFile> albumPic;

    @JsonIgnore
    private int ialbum;
}
