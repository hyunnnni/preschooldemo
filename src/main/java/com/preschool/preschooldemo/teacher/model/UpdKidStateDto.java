package com.preschool.preschooldemo.teacher.model;

import com.preschool.preschooldemo.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@Schema(title = "관리자 입장에서 원아 재원 상태 / 반 승급 수정 시 필요한 데이터")
public class UpdKidStateDto {
    @Schema(title = "원아PK(여러 개 선택가능)")
    @NotBlank(message = "원아를 선택해주세요")
    private List<Integer> ikids;
    @Schema(title = "이 페이지에 접근하는 유저의 등급 PK")
    @Range(min = Const.TEACHER, max = Const.BOSS, message = "접근할 권한이 없습니다")
    @NotBlank(message = "접근 유저의 등급을 넣으세요")
    private int ilevel;
    @Schema(title = "원아의 재원상태 OR 원아 승급 반")
    @NotBlank(message = "원하는 재원상태 OR 반을 선택해주세요")
    @Range(min = Const.STATE_DROP_OUT, max = Const.CLASS_GENERAL,
            message = "원하는 재원상태 OR 반을 선택해주세요")
    private int kidCheck;
}
