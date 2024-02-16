package com.preschool.preschooldemo.fullnotice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "소식 조회")
public class AllFullNoticeSelVo {
    @Schema(title = "공지 개수")
    private int noticeFixCnt;
    @Schema(title = "소식 개수")
    private int noticeCnt;
    @Schema(title = "소식 리스트")
    private List<SelFullNoticeVo> list;
}
