package com.preschool.preschooldemo.notice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(title = "알림장 댓글 작성에 필요한 요청 데이터")
public class InsNoticeCommentDto {
    @Schema(title = "댓글을 작성할 알림장 pk")
    @Positive(message="잘못된 값입니다")
    private int inotice;
    @Schema(title = "댓글 내용")
    @NotBlank(message = "댓글 내용을 입력해주세요")
    private String noticeComment;
    @Schema(title = "작성자 pk (관리자 작성 시)")
    @PositiveOrZero(message="잘못된 값입니다")
    private int iteacher;
    @Schema(title = "작성자 pk (학부모 작성 시)")
    @PositiveOrZero(message="잘못된 값입니다")
    private int iparent;
    @JsonIgnore
    private int ilevel;
}

