package com.preschool.preschooldemo.teacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.preschool.preschooldemo.common.utils.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@Schema(title = "관리자 입장에서 원아 재원 상태 / 반 승급 수정 시 필요한 데이터")
public class UpdKidStateDto {
    @Schema(title = "원아PK(여러 개 선택가능)")
    private List<Integer> ikids;
    @JsonIgnore
    private int ilevel;
    @Schema(title = "원아의 재원상태 OR 원아 승급 반")
    @Range(min = Const.STATE_DROP_OUT, max = Const.CLASS_GENERAL,
            message = "원하는 재원상태 OR 반을 선택해주세요")
    private int kidCheck;
}
