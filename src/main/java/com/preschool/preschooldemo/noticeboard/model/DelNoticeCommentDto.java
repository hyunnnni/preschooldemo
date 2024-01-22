package com.preschool.preschooldemo.noticeboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(title = "알림장 댓글 삭제 요청 데이터")
public class DelNoticeCommentDto {
    @Schema(title = "삭제하고 싶은 댓글 pk")
    @Min(value = 1 ,message = "잘못된 댓글 pk입니다")
    private int inoticeComment;
    @Schema(title = "삭제를 원하는 유저의 pk(관리자)")
    private int iteacher;
    @Schema(title = "삭제를 원하는 유저의 pk(선생님)")
    private int iparent;
    @Schema(title = "삭제를 원하는 유저의 등급")
    @Range(min = 1, max = 3, message = "삭제할 권한이 없음")
    private int ilevel;
}
