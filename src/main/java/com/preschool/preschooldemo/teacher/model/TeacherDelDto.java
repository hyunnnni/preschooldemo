package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TeacherDelDto {
    @Schema(title = "선생님PK(여러 개 선택 가능)")
    private List<Integer> iteachers;
    @JsonIgnore
    @Schema(title = "삭제 처리")
    private int tcIsDel = Const.FAKE_IS_DEL;
    @JsonIgnore
    private int ilevel;
}

