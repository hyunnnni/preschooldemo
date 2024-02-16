package com.preschool.preschooldemo.fullnotice;

import com.preschool.preschooldemo.fullnotice.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.preschool.preschooldemo.common.utils.ResVo;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/full")
@Tag(name = "유치원 소식 기능 API", description = "유치원 소식 관련 기능 설정")
public class FullNoticeController {
    private final FullNoticeService service;

    //---------------------- 유치원 소식 전체 리스트 조회 -------------------

    @GetMapping("/listall")
    @Operation(summary = "유치원 전체 소식 불러오기", description = "<strong>게시판 전체불러오기</strong><br><br>" +
            "게시판 전체보내기<br>" +
            "성공시 페이지 띄우기<br>" +
            "실패시 에러메세지송출<br>")

    public AllFullNoticeSelVo getAllFullNotice(@RequestParam @Schema(title = "페이지")
                                                   int page
                                               ,@RequestParam(required = false) @Schema(title = "검색어")
                                                   String search) {

        SelFullNoticeDto dto = new SelFullNoticeDto();
        dto.setPage(page);
        dto.setSearch(search);
        return service.getAllFullNotice(dto);
    }
    //-------------------------------- 유치원 소식 조회 --------------------------------
    @GetMapping
    @Operation(summary = "유치원 소식 불러오기", description = "<strong>소식 불러오기</strong><br><br>" +
            "유치원 소식<br>" +
            "성공시 페이지 띄우기<br>" +
            "실패시 에러메세지송출<br>")
    public SelNoticeVo getNotice(@RequestParam @Schema(title = "게시글")int iFullNotice) {
        return service.getFullNotice(iFullNotice);
    }

    //-------------------------------- 유치원 소식 작성 --------------------------------
    @PostMapping
    @Operation(summary = "3차 유치원 소식 작성", description = """
            사진 개수 제한 기능 추가<br>
            리스트 안 result 값이<br>
            1 : 글만 작성 성공<br>
            1 이상 : 정상적으로 작성 성공""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo postFullNotice(@RequestPart(required = false) List<MultipartFile> pics,
                                @Valid @RequestPart InsFullNoticeDto dto) {
        dto.setFullPic(pics);
        return service.postFullNotice(dto);
    }

    //-------------------------------- 유치원 소식 삭제 --------------------------------
    @DeleteMapping
    @Valid
    @Operation(summary = "유치원 소식 삭제", description = """
            리스트 안 result 값이<br>
            1 : 삭제 성공""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo delFullNotice(@RequestParam
                               @Schema(title = "삭제를 원하는 유저(관리자만)의 pk")
                               @Positive(message = "잘못된 값입니다")
                               int iteacher,
                               @RequestParam
                               @Schema(title = "삭제 대상 유치원 소식")
                               @Positive(message = "잘못된 값입니다")
                               int ifullNotice) {
        DelFullNoticeDto dto = new DelFullNoticeDto();
        dto.setIfullNotice(ifullNotice);
        dto.setIteacher(iteacher);
        return service.delFullNotice(dto);
    }

    //-------------------------------- 유치원 소식 수정 --------------------------------
    @PutMapping
    @Operation(summary = "3차 유치원 소식 수정", description = """
            사진 개수 제한 기능 추가<br>
            리스트 안 result 값이<br>
            1 이상 : 사진과 글 성공<br>
            1 : (글만 있는 소식일 시) 수정 성공""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo putFullNotice(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart @Valid UpdFullNoticeDto dto) {
        dto.setFullPic(pics);
        return service.putFullNotice(dto);
    }

    //-------------------------------- 유치원 소식 수정 시 불러오기 --------------------------------
    @GetMapping("/edit")
    @Valid
    @Operation(summary = "유치원 소식 수정 시 불러오기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public SelFullNoticeUpdVo getFullNoticeUpd(
                                    @RequestParam
                                    @Schema(title = "대상 유치원 소식")
                                    @Positive(message = "잘못된 값입니다")
                                    int ifullNotice) {
        SelFullNoticeUpdDto dto = new SelFullNoticeUpdDto();
        dto.setIfullNotice(ifullNotice);
        return service.getFullNoticeUpd(dto);
    }

}
