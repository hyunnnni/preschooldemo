package com.preschool.preschooldemo.memory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class InsMemoryDto {
    @Schema(title = "태그 대상 원아 PK")
    private List<Integer> ikids;

    @Schema(title = "추억 앨범 제목")
    @NotBlank(message = "값을 입력해주세요")
    private String memoryTitle;

    @Schema(title = "추억 앨범 내용")
    private String memoryContent;

    @JsonIgnore
    private int iteacher;

    @JsonIgnore
    private int imemory;
}
