package com.preschool.preschooldemo.album;

import com.preschool.preschooldemo.album.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlbumMapper {
    //------------------------------------- 활동 앨범 전체 조회 -------------------------------------
    List<AlbumSelVo> selAllAlbum (AlbumSelDto dto);
    //------------------------------------- 활동 앨범 개수 -------------------------------------
    int selAlbumCnt ();
    //------------------------------------- 활동 앨범 상세 조회 (글) -------------------------------------
    AlbumDetailSelVo selDetailAlbum (AlbumDetailSelDto dto);
    //------------------------------------- 활동 앨범 상세 조회 (사진) -------------------------------------
    List<String> selPicsAlbum (AlbumDetailSelDto dto);
    //------------------------------------- 활동 앨범 상세 조회 (댓글) -------------------------------------
    List<AlbumAllCommentVo> selCommentAlbum (AlbumDetailSelDto dto);
    //------------------------ 알림장 작성자 pk로 이름 조회(관리자) ------------------------
    AlbumSelCommentProc selAlbumDetailTea (int iteacher);
    //------------------------ 알림장 작성자 pk로 이름 조회(부모님) ------------------------
    AlbumSelCommentProc selAlbumDetailPar (int iparent);
    //------------------------------------- 활동 앨범 등록 -------------------------------------
    int insAlbum (AlbumInsDto dto);
    //------------------------------------- 활동 앨범 사진 등록 -------------------------------------
    int insAlbumPic (AlbumPicsInsDto dto);
    //------------------------------------- 활동 앨범 글 내용 가져오기 -------------------------------------
    AlbumDeSelVo selAlbumContent(int ialbum);
    //------------------------------------- 활동 앨범 글 수정 시 사진 조회 -------------------------------------
    List<AlbumPicsVo> albumEditPics(int ialbum);
    //------------------------------------- 활동 앨범 수정-------------------------------------
    int updAlbum (AlbumUpdDto dto);
    //------------------------------------- 활동 앨범 수정(사진) -------------------------------------
    int delAlbumPic(List<Integer> delPics);
    //------------------------------------- 활동 앨범 사진과 댓글 삭제 -------------------------------------
    int delAlbumCommentPics (AlbumDelDto dto);
    //------------------------------------- 활동 앨범 글 삭제  -------------------------------------
    int delAlbumAll (AlbumDelDto dto);
    //------------------------------------- 활동 앨범 댓글 등록 -------------------------------------
    int insAlbumComment (AlbumCommentInsDto dto);
    //------------------------------------- 활동 앨범 댓글 삭제 -------------------------------------
    int delAlbumComment (AlbumDelCommentDto dto);

}
