package com.preschool.preschooldemo.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemoryPushVo {
    @Schema(title = "추억앨범 업로드한 유저 pk")
    private int writerIuser;
    @Schema(title = "추억앨범 업로드한 유저 이름")
    private int writerNm;
    @Schema(title = "추억앨범 제목")
    private String memoryTitle;
    @Schema(title = "추억앨범 작성 시 푸시알림이 가는 날짜")
    private String createdAt;
    @Schema(title = "추억앨범 PK")
    private int imemory;
    @Schema(title = "추억앨범 대상 원아PK")
    private int ikid;
    @Schema(title = "추억앨범 대상 원아 이름")
    private String kidNm;
}
