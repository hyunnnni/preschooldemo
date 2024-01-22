package com.preschool.preschooldemo.noticeboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(title = "알림장 댓글 작성에 필요한 요청 데이터")
public class InsNoticeCommentDto {
    @Schema(title = "댓글을 작성할 알림장 pk")
    @Min(value = 1, message = "잘못된 값입니다")
    private int inotice;
    @Schema(title = "댓글 내용")
    private String noticeComment;
    @Schema(title = "작성자 pk (관리자 작성 시)")
    private int iteacher;
    @Schema(title = "작성자 pk (학부모 작성 시)")
    private int iparent;
    @Schema(title = "등급 pk")
    @Range(min = 1 ,max = 3, message = "해당 페이지에 접근할 권한 없음")
    private int ilevel;
}

