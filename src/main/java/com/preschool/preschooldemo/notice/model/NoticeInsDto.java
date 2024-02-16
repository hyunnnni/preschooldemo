package com.preschool.preschooldemo.notice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "알림장 등록에 필요한 요청 데이터")
public class NoticeInsDto {
    @Schema(title = "원아 PK")
    private List<Integer> ikids;
    @Schema(title = "알림장 제목")
    @NotBlank(message = "값을 입력해주세요")
    private String noticeTitle;
    @Schema(title = "알림장 내용")
    @NotBlank(message = "값을 입력해주세요")
    private String noticeContents;
    @Schema(title = "중요 알림장")
    private int noticeCheck;
}
