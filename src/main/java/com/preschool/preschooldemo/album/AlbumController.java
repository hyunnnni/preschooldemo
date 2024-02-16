package com.preschool.preschooldemo.album;

import com.preschool.preschooldemo.album.model.*;
import com.preschool.preschooldemo.common.utils.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/album")
@Tag(name = "활동 앨범 API", description = "활동 앨범 관련")
public class AlbumController {
    private final AlbumService service;

    //------------------------------------- 활동 앨범 등록 -------------------------------------
    @Operation(summary = "활동 앨범 등록", description = """
            -1: 실패<br> 1: 성공
            """)
    @PostMapping
    public ResVo postAlbum(@RequestPart List<MultipartFile> pics,
                           @Valid @RequestPart AlbumInsDto dto) {
        dto.setAlbumPic(pics);
        return service.postAlbum(dto);
    }

    //------------------------------------- 활동 앨범 전체 조회 -------------------------------------
    @Operation(summary = "활동 앨범 전체 조회")
    @Valid
    @GetMapping("/listall")
    public AllAlbumSelVo getAllAlbum(@RequestParam
                                        @Positive(message = "잘못된 값입니다")
                                        @Schema(title = "페이지")
                                        int page,
                                        @RequestParam(required = false)
                                        @Schema(title = "검색어")
                                        String search) {
        AlbumSelDto dto = new AlbumSelDto();
        dto.setPage(page);
        dto.setSearch(search);
        return service.getAllAlbum(dto);
    }

    //------------------------------------- 활동 앨범 상세 조회 -------------------------------------
    @Operation(summary = "활동 앨범 상세 조회")
    @Valid
    @GetMapping
    public AlbumDetailSelVo getDetailAlbum(@RequestParam
                                                 @Positive(message = "잘못된 값입니다")
                                                 @Schema(title = "앨범 PK")
                                                 int ialbum) {
        AlbumDetailSelDto dto = new AlbumDetailSelDto();
        dto.setIalbum(ialbum);
        return service.getDetailAlbum(dto);
    }


    //------------------------------------- 활동 앨범 글 삭제 -------------------------------------
    @Operation(summary = "활동 앨범 글 삭제", description = """
            -1: 실패<br> 1: 성공
            """)
    @Valid
    @DeleteMapping
    public ResVo delAlbum(@RequestParam
                          @Positive(message = "잘못된 값입니다")
                          @Schema(title = "앨범 PK")
                          int ialbum) {
        AlbumDelDto dto = new AlbumDelDto();
        dto.setIalbum(ialbum);
        return service.delAlbum(dto);
    }


    //------------------------------------- 활동 앨범 댓글 등록 -------------------------------------
    @Operation(summary = "활동 앨범 댓글 등록", description = """
            -1: 실패<br> 1: 성공
            """)
    @PostMapping("/comment")
    public ResVo postAlbumComment(@RequestBody @Valid AlbumCommentInsDto dto) {
        return service.postAlbumComment(dto);
    }


    //------------------------------------- 활동 앨범 댓글 삭제 -------------------------------------
    @Operation(summary = "활동 앨범 댓글 삭제", description = """
            -1: 실패<br> 1: 성공
            """)
    @DeleteMapping("/comment")
    public ResVo delAlbumComment(@RequestParam @Positive(message = "잘못된 값입니다")
                                 @Schema(title = "앨범 댓글 PK")
                                 int ialbumComment,
                                 @RequestParam
                                 @Positive(message = "잘못된 값입니다")
                                 @Schema(title = "앨범 PK")
                                 int ialbum,
                                 @RequestParam(required = false, defaultValue = "0")
                                 @Schema(title = "선생님 PK")
                                 int iteacher,
                                 @RequestParam(required = false, defaultValue = "0")
                                 @Schema(title = "선생님 PK")
                                 int iparent) {
        AlbumDelCommentDto dto = new AlbumDelCommentDto();
        dto.setIalbumComment(ialbumComment);
        dto.setIalbum(ialbum);
        dto.setIteacher(iteacher);
        dto.setIparent(iparent);
        return service.delAlbumComment(dto);
    }

    //------------------------------------- 활동 앨범 수정 -------------------------------------
    @Operation(summary = "활동 앨범 수정", description = """
            수정할 글과 사진 수정
            """)
    @PutMapping
    public ResVo putAlbum(@RequestPart(required = false) List<MultipartFile> pics, @Valid @RequestPart AlbumUpdDto dto) {
        return service.putAlbum(pics, dto);
    }

    //------------------------------------- 활동 앨범 수정 시 정보 조회 -------------------------------------
    @Operation(summary = "활동 앨범 수정 시 정보 조회", description = """
            수정 시 수정할 글과 사진 정보 출력
            """)
    @Valid
    @GetMapping("/edit")
    public AlbumDeSelVo albumEdit(@RequestParam
                                  @Positive(message = "잘못된 값입니다")
                                  @Schema(title = "앨범 PK")
                                  int ialbum) {
        return service.albumEdit(ialbum);
    }
}

