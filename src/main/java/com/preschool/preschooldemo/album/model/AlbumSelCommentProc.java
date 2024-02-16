package com.preschool.preschooldemo.album.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "활동 앨범 상세 조회 댓글 정보")
public class AlbumSelCommentProc {
    @Schema(title = "활동 앨범 댓글 PK")
    private int ialbumComment;
    @Schema(title = "활동 앨범 댓글 내용")
    private String albumComment;
    @Schema(title = "작성자의 등급 pk 구별")
    private int ilevel;
    @Schema(title = "작성자의 pk")
    private String writerIuser;
    @Schema(title = "작성자의 이름")
    private String writerName;
    @Schema(title = "알림장 댓글 작성일")
    private String createdAt;

}
