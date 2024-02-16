package com.preschool.preschooldemo.notice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "알림장 수정")
public class NoticeUpdDto {
    @Schema(title = "알림장 PK")
    @Positive(message = "잘못된 값입니다")
    private int inotice;
    @JsonIgnore
    private int iteacher;
    @Schema(title = "원아 PK")
    @Positive(message = "잘못된 값입니다")
    private int ikid;
    @Schema(title = "알림장 제목")
    @NotBlank(message = "값을 입력해주세요")
    private String noticeTitle;
    @Schema(title = "알림장 내용")
    @NotBlank(message = "값을 입력해주세요")
    private String noticeContents;
    @Schema(title = "알림장 사진PK")
    private List<Integer> inoticePic;
    @Schema(title = "알림장 사진")
    @JsonIgnore
    List<String> noticePics;
}
