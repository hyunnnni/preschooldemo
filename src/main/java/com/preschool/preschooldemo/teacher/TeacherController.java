package com.preschool.preschooldemo.teacher;

import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.teacher.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;


import java.security.Provider;
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
    @Valid
    @Operation(summary = "원아 관리 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SelKidManagementVo> getKidManagement(
            @RequestParam @Positive(message = "잘못된 값입니다")
            @NotBlank(message = "잘못된 값입니다")
            @Schema(title = "페이징 시 필요한 데이터")
            int page,
            @RequestParam @Range(min = Const.STATE_DROP_OUT, max = Const.CLASS_GENERAL,
                    message = "원하는 재원상태 OR 반을 선택해주세요")
            @NotBlank(message = "잘못된 값입니다")
            @Schema(title = "조회 시 선택하는 반 전체 조회 시 값 필요없음")
            int kidCheck) {
        SelKidManagementDto dto = new SelKidManagementDto();
        dto.setPage(page);
        dto.setKidCheck(kidCheck);
        return service.getKidManagement(dto);
    }

    //-------------------------------- 원아 재원 상태 / 반 승급 수정 --------------------------------
    @PatchMapping("/stateorclass")
    @Operation(summary = "원아 재원 상태 / 반 승급 수정", description = """
            result 값이<br>
            -1 : 원아 상태 수정/ 부모님 연결 끊기 실패<br>
            1 이상 : 수정 성공한 원아의 수""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo patchKidStateOrClass(@RequestBody @Valid UpdKidStateDto dto) {
        return service.patchKidStateOrClass(dto);
    }

    //-------------------------------- 학부모 관리 페이지 조회 --------------------------------
    @GetMapping("/parent")
    @Valid
    @Operation(summary = "학부모 관리 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SelParManagementVo> getParentManagement(
            @RequestParam @Positive(message = "잘못된 값입니다")
            @NotBlank(message = "잘못된 값입니다")
            @Schema(title = "페이징 시 필요한 데이터")
            int page,
            @RequestParam @Positive(message = "잘못된 값입니다")
            @NotBlank(message = "잘못된 값입니다")
            @Schema(title = "조회 시 선택하는 반 전체 조회 시 값 필요없음")
            int iclass) {
        SelParManagementDto dto = new SelParManagementDto();
        dto.setPage(page);
        dto.setIclass(iclass);
        return service.getParentManagement(dto);
    }

    //-------------------------------- 학부모 정보 관리자가 삭제 --------------------------------
    @PutMapping("/parent")
    @Operation(summary = "학부모 정보 관리자가 삭제", description = """
            result 값이<br>
            1 이상 : 삭제 처리된 계정 수""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo delParent(@RequestBody @Valid DelParentDto dto) {
        return service.delParent(dto);
    }

    //-------------------------------- 학부모와 원아 연결 끊기 --------------------------------
    @DeleteMapping("/Disconnent")
    @Valid
    @Operation(summary = "학부모와 원아 연결 끊기", description = """
            result 값이<br>
            1 : 연결 끊기 성공""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo delDisconnect(@RequestParam@Positive(message="잘못된 값입니다")
                                @NotBlank(message = "잘못된 값입니다")
                                @Schema(title = "연결을 끊을 학부모PK")
                                int iparent,
                                @RequestParam
                                @Positive(message = "잘못된 값입니다")
                                @NotBlank(message = "잘못된 값입니다")
                                @Schema(title = "연결을 끊을 원아PK")
                                int ikid) {
        DelDisconnectDto dto = new DelDisconnectDto();
        dto.setIparent(iparent);
        dto.setIkid(ikid);
        return service.delDisconnect(dto);
    }


}
