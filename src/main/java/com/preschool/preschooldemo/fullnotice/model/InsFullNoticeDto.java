package com.preschool.preschooldemo.fullnotice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Schema(title = "유치원 소식 작성에 필요한 데이터")
public class InsFullNoticeDto {
    @JsonIgnore
    @Schema(title = "응답값 유치원 소식 pk")
    private int ifullNotice;
    @Schema(title = "유치원 소식 제목")
    @NotBlank(message = "값을 입력해주세요")
    private String fullTitle;
    @Schema(title = "유치원 소식 내용")
    @NotBlank(message = "값을 입력해주세요")
    private String fullContents;
    @Schema(title = "유치원 소식 상단 공지 체크 : 1, 체크 해제 : 0")
    private int fullNoticeFix;
    @Schema(title = "작성 관리자 pk")
    @Positive(message = "잘못된 값입니다")
    private int iteacher;
    @JsonIgnore
    @Schema(title = "유치원 소식 사진")
    private List<MultipartFile> fullPic;
    @JsonIgnore
    private int ilevel;
}
