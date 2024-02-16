package com.preschool.preschooldemo.notice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "알림장 댓글 삭제 요청 데이터")
public class DelNoticeCommentDto {
    @Schema(title = "삭제하고 싶은 댓글 pk")
    private int inoticeComment;
    @Schema(title = "로그인 유저의 pk(관리자일 때)")
    private int iteacher;
    @Schema(title = "로그인 유저의 pk(학부모일 때)")
    private int iparent;
    @JsonIgnore
    private int ilevel;
}
