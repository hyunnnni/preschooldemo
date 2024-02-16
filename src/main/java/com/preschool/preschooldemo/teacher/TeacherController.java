package com.preschool.preschooldemo.teacher;

import com.preschool.preschooldemo.common.utils.ResVo;
import com.preschool.preschooldemo.parent.model.UserFirebaseTokenPatchDto;
import com.preschool.preschooldemo.teacher.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    @Operation(summary = "원아 관리 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public SelKidManagementVo getKidManagement(SelKidManagementDto dto) {
        return service.getKidManagement(dto);
    }

    //-------------------------------- 원아 재원 상태 / 반 승급 수정 --------------------------------
    @PatchMapping("/stateorclass")
    @Operation(summary = "원아 재원 상태 / 반 승급 수정", description = """
            result 값이<br>
            -1 : 원아 상태 수정/ 부모님 연결 계정 삭제 실패<br>
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
    @Operation(summary = "학부모 관리 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public SelParManagementVo getParentManagement(SelParManagementDto dto) {
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
    @DeleteMapping("/disconnect")
    @Operation(summary = "학부모와 원아 연결 끊기", description = """
            result 값이<br>
            1 : 연결 끊기 성공""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo delDisconnect(DelDisconnectDto dto) {
        return service.delDisconnect(dto);
    }

    //-------------------------------- 선생님 정보 수정 불러오기 --------------------------------
    @GetMapping("/edit")
    @Valid
    @Operation(summary = "선생님 정보 수정 시 불러오기", description = """
            수정할 선생님 정보 불러오기
            """)
    public TeacherEditVo selTeacherEdit(@RequestParam
                                        @Schema(title = "선생님 PK")
                                        int iteacher) {
        return service.selTeacherEdit(iteacher);
    }

    //-------------------------------- 선생님 정보 수정 --------------------------------
    @PutMapping
    @Operation(summary = "3차 선생님 정보 수정", description = """
            -1 : 하나의 값도 변경되지 않음<br>
            1 : 성공
            """)
    public ResVo putTeacher(@RequestPart(required = false) MultipartFile pic, @Valid @RequestPart TeacherPatchDto dto) {
        return service.putTeacher(pic, dto);
    }

    //-------------------------------- 선생님 정보 삭제 --------------------------------
    @PatchMapping
    @Operation(summary = "3차 선생님 정보 삭제", description = """
            -1 : 실패<br>
            1: 성공
            """)
    public ResVo delTeacher(TeacherDelDto dto) {
        return service.delTeacher(dto);
    }

    //-------------------------------- 선생님 로그인 --------------------------------

    @PostMapping("/signin")
    @Operation(summary = "로그인", description = "<strong>선생님 로그인</strong><br><br>" +
            "uid와 upw로 로그인<br>" +
            "성공시 선생님 PK 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public TeacherEntity postTeacherSignin(HttpServletRequest req, HttpServletResponse res
            , @RequestBody TeacherSigninDto dto) {
        return service.teacherSignin(req, res, dto);
    }
    //-------------------------------- 선생님 부모정보 수정 --------------------------------
    @PutMapping("/parentedit")
    @Operation(summary = "선생님이 부모님 정보 수정")
    public ResVo putTeacherParent(@RequestBody @Valid UpdTeacherParentDto dto){
        return service.putTeacherParent(dto);
    }
    //-------------------------------- 선생님 부모정보 수정시 정보가져오기 --------------------------------
    @GetMapping("/parentedit")
    @Operation(summary = "수정 전 정보가져오기", description = "<strong>정보 불러오기</strong><br><br>" +
            "수정전 정보가져오기<br>" +
            "성공시 원래 정보 가져오기, 부모 PK 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public TeacherParentBeforInfoVo getTeacherParentEdit(int iparent){
        return service.getTeacherParentEdit(iparent);
    }
    //-------------------------------- 리프레시 토큰 --------------------------------
    @GetMapping("/refresh-token")
    public TeacherEntity getRefreshToken(HttpServletRequest req){
        return service.getRefreshToken(req);
    }

    //------------------------------ 3차 원장님이 선생님 등록 ------------------------------------
    @PostMapping("/signup")
    @Operation(summary = "원장님이 선생님 등록", description = "<strong>선생님 회원가입</strong><br><br>" +
            "이상없을 경우 회원가입 성공<br>" +
            "성공시 선생님PK로 응답<br>" +
            "실패시 -1 or 에러메세지 송출 <br>")
    public ResVo postTeacher( @RequestPart MultipartFile pic,@RequestPart @Valid TeacherInsDto dto){

        return service.insTeacher(pic,dto);
    }
    //-------------------------------- 파이어베이스 토큰 --------------------------------
    @PatchMapping("/firebase-token")
    public ResVo patchTeaFirebaseToken(@RequestBody UserFirebaseTokenPatchDto dto) {
        return service.patchTeaFirebaseToken(dto);
    }
    //------------------------- 3차 전체 선생님 불러오기 -----------------------------------------

    @GetMapping
    @Operation(summary = "전체 선생님 정보 불러오기", description = "<strong>전체 선생님 불러오기</strong><br><br>" +
            "성공시 화면띄우기<br>" +
            "실패시 에러메세지 송출 <br>")
    public List<SelAllTeacherVo> selAllTeacher(SelAllTeacherDto dto){
        return service.selAllTeacher(dto);
    }


}
