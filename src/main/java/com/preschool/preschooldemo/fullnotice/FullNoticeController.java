package com.preschool.preschooldemo.fullnotice;

import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.fullnotice.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/full")
@Slf4j
@Tag(name = "유치원 소식 기능 API", description = "유치원 소식 관련 기능 설정")
public class FullNoticeController {
    private final FullNoticeService service;

    //-------------------------------- 유치원 소식 작성 --------------------------------
    @PostMapping
    @Operation(summary = "유치원 소식 작성", description = """
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
    @Operation(summary = "유치원 소식 수정", description = """
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
    @GetMapping
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
