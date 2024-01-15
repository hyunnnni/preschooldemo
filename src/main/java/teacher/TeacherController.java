package teacher;

import common.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import teacher.model.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
@Slf4j
@Tag(name = "관리자 기능 API", description = "관리자 기준 화면 기능 처리")
public class TeacherController {
    private final TeacherService service;

//-------------------------------- 원아 관리 페이지 조회 --------------------------------
    @GetMapping("/kid")
    @Operation(summary = "원생관리페이지 원아 조회", description = """
    리스트 안 result 값이
    -2 : 관리자 외 계정으로 접근 시 거부 에러<br>
    -1 : 조회 실패<br>
    0 : 이상 없음""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SelKidManagementVo> getKidManagement(SelKidManagementDto dto){
        return service.getKidManagement(dto);
    }

//-------------------------------- 원아 재원 상태 수정 --------------------------------
    @PatchMapping("/state")
    @Operation(summary = "원아 재원 상태 수정", description = """
    리스트 안 result 값이
    -2 : 관리자 외 계정으로 접근 시 거부 에러<br>
    -1 : 수정 실패<br>
    1 : 수정 성공""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo patchKidState (UpdKidStateDto dto){
        return service.patchKidState(dto);
    }

//-------------------------------- 원아 반 승급 수정 --------------------------------

}
