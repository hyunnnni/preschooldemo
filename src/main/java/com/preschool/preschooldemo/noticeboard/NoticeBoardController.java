package com.preschool.preschooldemo.noticeboard;

import com.preschool.preschooldemo.noticeboard.model.SelAllNoticeDto;
import com.preschool.preschooldemo.noticeboard.model.SelAllNoticeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
@Slf4j
@Tag(name = "알림장 기능 API", description = "알림장 관련 기능 설정")
public class NoticeBoardController {
    private final NoticeBoardService service;

    //-------------------------------- 알림장 접근 유저에 따라 다르게 전체 조회 --------------------------------
    @GetMapping
    @Operation(summary = "유치원 알림장 현재 년도만 조회", description = """
    로그인 유저에 따라 연결 원아 기준으로만 조회되거나 해당 선생님의 반 원아만 조회, 원장님은 전체 조회<br>
    리스트 안 result 값이<br>
    -3 : 해당 정보로 조회 시 조회되는 정보 없음<br>
    -2 : 관리자 외 계정으로 접근 시 거부 에러<br>
    0 : 이상 없음<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<SelAllNoticeVo> getAllNotice(SelAllNoticeDto dto){
        return service.getKidManagement(dto);
    }



}

