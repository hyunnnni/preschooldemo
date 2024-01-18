package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@Schema(title = "관리자 입장에서 학부모 계정 삭제 시 필요한 데이터")
public class DelParentDto {
    @Schema(title = "학부모PK(여러 개 선택가능)")
    private List<Integer> iparents;
    @Schema(title = "이 페이지에 접근하는 유저의 등급 PK")
    private int ilevel;
    @JsonIgnore
    @Schema(title = "삭제 처리")
    private int prIsDel = Const.FAKE_IS_DEL;
}
