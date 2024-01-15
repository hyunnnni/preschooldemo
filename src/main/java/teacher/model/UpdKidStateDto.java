package teacher.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "관리자 입장에서 원자 재원 상태 수정 시 필요한 데이터")
public class UpdKidStateDto {
    @Schema(title = "원아PK(여러 개 선택가능)")
    private List<Integer> ikids;
    @Schema(title = "직급 PK")
    private int irank;
    @Schema(title = "원아의 재원상태 OR 원아 승급 반")
    private int kidCheck;
}
