package com.preschool.preschooldemo.memory;


import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.utils.ResVo;
import com.preschool.preschooldemo.memory.model.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memory")
@Tag(name = "추억앨범", description = "추억앨범 관리")
public class MemoryController {
    private final MemoryService service;
    private final AuthenticationFacade authenticationFacade;

    @GetMapping
    public AllMemoryVo getAllMemory(AllSelMemoryDto dto){
        return service.allMemory(dto);
    }

    @GetMapping("/detail")
    public AllSelMemoryVo getMemory(int imemory){
        return service.memory(imemory);
    }


    @GetMapping("/eidt")
    @Operation(summary = "추억앨범 정보 불러오기", description = "추억앨범 수정 전 정보 불러오기")
    public SelMemoryVo getMemoryEdit(@RequestParam int imemory){
//        List<String> roles = authenticationFacade.getRoles();
//        if(!(roles.get(0).equals("admin"))){
//            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
//        }
        return service.selMemory(imemory);
    }
    //-------------------------------- 추억 앨범 등록 --------------------------------
    @PostMapping
    @Operation(summary = "3차 추억 앨범 등록", description = """
            선생님만 등록 가능<br>
            사진 최대 20장 등록 가능
            사진 업로드 필수
                    <select>
                    <option>1(좋아요)</option>
                    <option>0(좋아요취소)</option>
                    </select>""")
    public ResVo postMemory(@RequestPart List<MultipartFile> pics, @RequestPart InsMemoryDto dto){
        return service.postMemory(pics, dto);
    }

    //-------------------------------- 추억 앨범 댓글 삭제 --------------------------------
    @DeleteMapping
    @Valid
    @Operation(summary = "3차 추억 앨범 등록", description = """
            선생님만 등록 가능<br>
            사진 최대 20장 등록 가능
            사진 업로드 필수
                    <select>
                    <option>1(좋아요)</option>
                    <option>0(좋아요취소)</option>
                    </select>""")
    public ResVo delMemoryComment(@RequestParam
                                      @Positive(message="잘못된 값입니다")
                                      @Schema(title = "삭제하고 싶은 댓글 pk")
                                      int imemoryComment,
                                      @RequestParam(required = false, defaultValue = "0")
                                      @PositiveOrZero(message = "잘못된 값입니다")
                                      @Schema(title = "삭제를 원하는 유저의 pk(관리자일 때)")
                                      int iteacher,
                                      @RequestParam(required = false, defaultValue = "0")
                                      @PositiveOrZero(message = "잘못된 값입니다")
                                      @Schema(title = "삭제를 원하는 유저의 pk(학부모일 때)")
                                      int iparent){
        DelMemoryCommentDto dto = new DelMemoryCommentDto();
        dto.setImemoryComment(imemoryComment);
        dto.setIteacher(iteacher);
        dto.setIparent(iparent);
        return service.delMemoryComment(dto);
    }
}
