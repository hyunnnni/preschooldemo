package com.preschool.preschooldemo.teacher.model;

import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "원아 졸업/퇴소 시 해당 학부모 계정 삭제 처리( 원아 2명 이상 일 시 남겨둠 )")
public class DelStateParentIsProc {
    @Schema(title = "삭제 처리")
    private int prIsDel = Const.FAKE_IS_DEL;
    @Schema(title = "부모 PK")
    private int iparent;
}
