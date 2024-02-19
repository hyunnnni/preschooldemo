package com.preschool.preschooldemo.memory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DelMemoryCommentDto {
    @Schema(title = "삭제하고 싶은 댓글 pk")
    private int imemoryComment;
    @Schema(title = "로그인 유저의 pk(관리자일 때)")
    private int iteacher;
    @Schema(title = "로그인 유저의 pk(학부모일 때)")
    private int iparent;
}
