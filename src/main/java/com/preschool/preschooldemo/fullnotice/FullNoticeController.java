package com.preschool.preschooldemo.fullnotice;

import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.fullnotice.model.DelFullNoticeDto;
import com.preschool.preschooldemo.fullnotice.model.InsFullNoticeDto;
import com.preschool.preschooldemo.fullnotice.model.UpdFullNoticeDto;
import com.preschool.preschooldemo.teacher.model.SelParManagementDto;
import com.preschool.preschooldemo.teacher.model.SelParManagementVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
    -5 : 사진 저장 실패<br>
    -1 : 작성 실패<br>
    1 : 글만 작성 성공<br>
    1 이상 : 정상적으로 작성 성공""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo postFullNotice (@RequestPart(required = false) List<MultipartFile> pics,
                                      @RequestPart InsFullNoticeDto dto){
        dto.setFullPic(pics);
        return service.postFullNotice(dto);
    }

//-------------------------------- 유치원 소식 삭제 --------------------------------
    @DeleteMapping
    @Operation(summary = "유치원 소식 삭제", description = """
        리스트 안 result 값이<br>
        1 : 삭제 성공<br>
        0 : 삭제 실패<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo delFullNotice(@Valid DelFullNoticeDto dto){
        return service.delFullNotice(dto);
    }

//-------------------------------- 유치원 소식 수정 --------------------------------
    @PutMapping
    @Operation(summary = "유치원 소식 수정", description = """
            리스트 안 result 값이<br>
            1 : 삭제 성공<br>
            0 : 삭제 실패<br>""")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "통신 성공"),
            @ApiResponse(responseCode = "400", description = "요청 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResVo putFullNotice(@RequestPart List<MultipartFile> fullPic, @RequestPart UpdFullNoticeDto dto){
        dto.setFullPic(fullPic);
        return service.putFullNotice(dto);
    }
}
