package com.preschool.preschooldemo.fullnotice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UpdFullNoticeDto {
    @Schema(title = "응답값 유치원 소식 pk")
    @Min(value = 1, message = "잘못된 값입니다")
    private int ifullNotice;
    @Schema(title = "등급 pk")
    @Range(min = Const.TEACHER, max = Const.BOSS, message = "글을 작성할 권한이 없습니다")
    private int ilevel;
    @Schema(title = "유치원 소식 제목")
    private String fullTitle;
    @Schema(title = "유치원 소식 내용")
    private String fullContents;
    @Schema(title = "유치원 소식 상단 공지 체크 : 1, 체크 해제 : 0")
    private int fullNoticeFix;
    @JsonIgnore
    @Schema(title = "유치원 소식 사진")
    private List<MultipartFile> fullPic;
    @Schema(title = "작성 관리자 pk")
    @Min(value = 1 ,message = "잘못된 작성자 pk입니다")
    private int iteacher;

}
