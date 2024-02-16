package com.preschool.preschooldemo.notice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "알림장 수정")
public class NoticeUpdSelVo {
    @Schema(title = "원아 PK")
    private int ikid;
    @Schema(title = "알림장 PK")
    private int inotice;
    @Schema(title = "알림장 제목")
    private String noticeTitle;
    @Schema(title = "알림장 내용")
    private String noticeContents;
    @Schema(title = "알림장 사진")
    List<NoticePicsVo> noticePics;
}
