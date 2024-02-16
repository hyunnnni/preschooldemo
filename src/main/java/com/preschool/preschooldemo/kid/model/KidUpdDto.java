package com.preschool.preschooldemo.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(title = "원아 정보 수정")
public class KidUpdDto {
    @Schema(title = "원아 pk")
    @Positive(message = "원아 번호를 입력해주세요")
    private int ikid;
    @Schema(title = "이름")
    @Pattern(regexp = "^[가-힣|a-z|A-Z]*$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String kidNm;
    @Schema(title = "반")
    @Range(min = 1,max = 4,message = "반을 확인해주세요")
    private int iclass;
    @Schema(title = "성별")
    @Range(min = 0,max = 1,message = "성별을 확인해주세요")
    private int gender;
    @JsonIgnore
    private String profile;
    @Schema(title = "출생일")
    @Pattern(regexp = "^([12]\\d{3})-(0[1-9]|1[012])-(0[0-9]|[12][0-9]|3[01])$",
            message = "형식이 맞지 않습니다 확인해주세요")
    private String birth;
    @Schema(title = "주소")
    @NotBlank
    private String address;
    @Schema(title = "메모")
    private String memo;
    @Schema(title = "비상 연락자")
    private String emerNm;
    @Schema(title = "비상 연락망")
    private String emerNb;
}
