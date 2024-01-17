package com.preschool.preschooldemo.teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "원아 졸업/퇴소 시 해당 학부모 계정 삭제 처리( 원아 2명 이상 일 시 남겨둠 )")
public class UpdKidStateParentProc {
    private int prIsDel;
    private int iparent;
}
