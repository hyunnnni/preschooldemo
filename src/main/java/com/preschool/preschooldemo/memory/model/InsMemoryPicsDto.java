package com.preschool.preschooldemo.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "추억 앨범 등록 중 사진 저장")
public class InsMemoryPicsDto {
    //추억 앨범에 등록될 사진
    private List<String> memoryPics = new ArrayList<>();
    //추억 앨범 PK
    private int imemory;
}
