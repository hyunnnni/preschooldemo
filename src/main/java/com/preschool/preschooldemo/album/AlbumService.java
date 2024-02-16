package com.preschool.preschooldemo.album;

import com.preschool.preschooldemo.album.model.*;
import com.preschool.preschooldemo.common.exception.AuthErrorCode;
import com.preschool.preschooldemo.common.exception.CommonErrorCode;
import com.preschool.preschooldemo.common.exception.PreschoolErrorCode;
import com.preschool.preschooldemo.common.exception.RestApiException;
import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.utils.Const;
import com.preschool.preschooldemo.common.utils.MyFileUtils;
import com.preschool.preschooldemo.common.utils.ResVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.preschool.preschooldemo.common.utils.Const.FAIL;
import static com.preschool.preschooldemo.common.utils.Const.SUCCESS;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    //------------------------------------- 활동 앨범 등록 -------------------------------------
    @Transactional
    public ResVo postAlbum(AlbumInsDto dto) {
        int level = authenticationFacade.getLevelPk();
        // 등급이 2, 3만 접근 가능하게 하며,
        // 원아 연결 없이 로그인만 가능한 0인 등급과 부모님은 글 작성 접근 제한
        if (level < 2) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        if (dto.getAlbumPic().size() > 20) {
            throw new RestApiException(AuthErrorCode.MANY_PIC);
        }
        try {
            int AffectedRows = mapper.insAlbum(dto);
            String target = "/album/" + dto.getIalbum();

            AlbumPicsInsDto pdto = new AlbumPicsInsDto();
            pdto.setIalbum(dto.getIalbum());

            for (MultipartFile file : dto.getAlbumPic()) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                pdto.getAlbumPic().add(saveFileNm);
            }

            int picAffectedRows = mapper.insAlbumPic(pdto);

            if (picAffectedRows > 0) {
                return new ResVo(dto.getIalbum());
            }

        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(FAIL);
    }

    //------------------------------------- 활동 앨범 전체 조회 -------------------------------------
    public AllAlbumSelVo getAllAlbum(AlbumSelDto dto) {
        int level = authenticationFacade.getLevelPk();
        // 등급이 1, 2, 3만 접근 가능하게 하며, 원아 연결 없이 로그인만 가능한 0인 등급 접근 제한
        if (level < 1) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try {
            List<AlbumSelVo> vo = mapper.selAllAlbum(dto);
            AllAlbumSelVo vo1 = new AllAlbumSelVo();
            vo1.setList(vo);
            int albumCnt = mapper.selAlbumCnt();
            vo1.setAlbumCnt(albumCnt);
            return vo1;
        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


    //------------------------------------- 활동 앨범 상세 조회 -------------------------------------
    public AlbumDetailSelVo getDetailAlbum(AlbumDetailSelDto dto) {

        int level = authenticationFacade.getLevelPk();
        if (level < 1) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        // 활동 앨범 글 불러오기
        AlbumDetailSelVo list = mapper.selDetailAlbum(dto);
        if (list == null) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }
        list.setAlbumPic(mapper.selPicsAlbum(dto));
        // 댓글 불러오기
        List<AlbumAllCommentVo> comList = mapper.selCommentAlbum(dto);
        List<AlbumSelCommentProc> comments = new ArrayList<>();

        if (comList.size() > 0) {
            for (AlbumAllCommentVo vo : comList) {
                if (vo.getIteacher() > 0 && vo.getIparent() == 0) {
                    AlbumSelCommentProc teaComment = mapper.selAlbumDetailTea(vo.getIteacher());
                    teaComment.setIalbumComment(vo.getIalbumComment());
                    teaComment.setAlbumComment(vo.getAlbumComment());
                    teaComment.setCreatedAt(vo.getCreatedAt());
                    comments.add(teaComment);
                }
                if (vo.getIparent() > 0 && vo.getIteacher() == 0) {
                    AlbumSelCommentProc parComment = mapper.selAlbumDetailPar(vo.getIparent());
                    parComment.setIalbumComment(vo.getIalbumComment());
                    parComment.setAlbumComment(vo.getAlbumComment());
                    parComment.setCreatedAt(vo.getCreatedAt());
                    comments.add(parComment);
                }
            }
        }
        list.setAlbumComments(comments);
        return list;
    }

    //------------------------------------- 활동 앨범 글 삭제 -------------------------------------
    @Transactional
    public ResVo delAlbum(AlbumDelDto dto) {

        int iteacher = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setIteacher(iteacher);
        dto.setIlevel(level);

        // 등급이 2, 3만 접근 가능하게 하며, 원아 연결 없이 로그인만 가능한 0인 등급과 1 부모님은 글 삭제 접근 제한
        if (dto.getIlevel() < 2) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try {
            int comAffectedRows = mapper.delAlbumCommentPics(dto);

            int delAffectedRows = mapper.delAlbumAll(dto);

            if (delAffectedRows == SUCCESS || comAffectedRows == SUCCESS) {
                return new ResVo(SUCCESS);
            }

        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(FAIL);
    }


    //------------------------------------- 활동 앨범 댓글 등록 -------------------------------------
    public ResVo postAlbumComment(AlbumCommentInsDto dto) {

        int loginUserPk = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();

        // 등급이 1, 2, 3만 접근 가능하게 하며, 원아 연결 없이 로그인만 가능한 0인 등급 접근 제한
        if (level < 1) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        if (level == 1) {
            dto.setIparent(loginUserPk);
        } else {
            dto.setIteacher(loginUserPk);
        }

        try {
            int affectedRows = mapper.insAlbumComment(dto);

            if (affectedRows > 0) {
                return new ResVo(SUCCESS);
            }

        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(FAIL);
    }

    //------------------------------------- 활동 앨범 댓글 삭제 -------------------------------------
    public ResVo delAlbumComment(AlbumDelCommentDto dto) {
        int loginUserPk = authenticationFacade.getLoginUserPk();

        if (dto.getIparent() > 0 && dto.getIteacher() > 0) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }

        try {
            int affectedRows = mapper.delAlbumComment(dto);

            if (affectedRows > 0) {
                return new ResVo(SUCCESS);
            }
        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(FAIL);
    }

    //------------------------------------- 활동 앨범 수정 -------------------------------------
    @Transactional
    public ResVo putAlbum(List<MultipartFile> pics, AlbumUpdDto dto) {

        int level = authenticationFacade.getLevelPk();
        // 등급이 2, 3만 접근 가능하게 하며, 원아 연결 없이 로그인만 가능한 0인 등급과 1 부모님은 글 수정 접근 제한
        if (level < 2) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        // 글 수정
        int updAfftectedRows = mapper.updAlbum(dto);

        // 사진 삭제
        if (dto.getDelPics() != null) {
            int delPicsAffectedRows = mapper.delAlbumPic(dto.getDelPics());
            if (delPicsAffectedRows == 0) {
                throw new RestApiException(AuthErrorCode.PICS_FAIL);
            }
        }
        //추가로 사진 업로드 하는 게 없다면 리턴
        if (pics == null) {
            return new ResVo(Const.SUCCESS);
        }

        // 사진 등록
        AlbumPicsInsDto picsDto = new AlbumPicsInsDto();
        picsDto.setIalbum(dto.getIalbum());

        String target = "/album/" + dto.getIalbum();
        myFileUtils.delFolderTrigger(target);
        if (pics.size() != 0) {
            for (MultipartFile file : pics) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                picsDto.getAlbumPic().add(saveFileNm);
            }
            int picsAffectedRows = mapper.insAlbumPic(picsDto);
            if (picsAffectedRows == 0 || pics.size() > 20) {
                throw new RestApiException(AuthErrorCode.MANY_PIC);
            }
        }
        return new ResVo(SUCCESS);
    }

    //------------------------------------- 활동 앨범 수정 시 정보 조회 -------------------------------------
    public AlbumDeSelVo albumEdit(int ialbum) {

        int ilevel = authenticationFacade.getLevelPk();

        if (ilevel < 1 || ilevel > 3) {
            throw new RestApiException(AuthErrorCode.NO_PERMISSION);
        }
        // 수정할 글 내용과 사진들 불러오기
        AlbumDeSelVo vo = mapper.selAlbumContent(ialbum);

        if (vo == null) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        List<AlbumPicsVo> pics = mapper.albumEditPics(ialbum);

        vo.setAlbumPic(pics);
        return vo;

    }
}