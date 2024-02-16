package com.preschool.preschooldemo.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "추억앨범 조회")
public class AllSelMemoryVo {
    @Schema(title = "추억앨범 PK")
    private int imemory;
    @Schema(title = "원아 PK")
    private List<Integer> ikids;
    @Schema(title = "추억앨범 제목")
    private String memoryTitle;
    @Schema(title = "추억앨범 내용")
    private String memoryContents;
    @Schema(title = "작성자")
    private String teacherNm;
    @Schema(title = "추억앨범 사진")
    private List<String> memoryPic;
    @Schema(title = "작성시간")
    private String createdAt;
    @Schema(title = "댓글여부")
    private int memoryComment;
    @Schema(title = "댓글")
    private List<String> memoryComments;

}
