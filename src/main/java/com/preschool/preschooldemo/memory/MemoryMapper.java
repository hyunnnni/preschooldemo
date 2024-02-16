package com.preschool.preschooldemo.memory;


import com.preschool.preschooldemo.memory.model.*;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoryMapper {
    List<AllSelMemoryVo> allMemoryTea(AllSelMemoryDto dto);
    int allMemoryTeaCnt(AllSelMemoryDto dto);
    List<AllSelMemoryVo> allMemoryPar(AllSelMemoryDto dto);
    int allMemoryParCnt(AllSelMemoryDto dto);
    int iMemoryComment(int imemory);
    List<Integer> iMemoryIkid(int imemory);

    // ---------------------- 수정전 정보 불러오기
    SelMemoryVo selMemory(int imemory);
    List<String> selMemoryPic(int imemory);
    String selImemory(int imemory);

    //-------------------------------- 원아와 연결된 부모 pk 조회 --------------------------------
    List<Integer> selKidConnectPar(List<Integer> ikids);
    //-------------------------------- 추억 앨범 글 등록 --------------------------------
    int insMemory(InsMemoryDto dto);
    //-------------------------------- 태그된 부모들 추억 앨범 글에 초대  --------------------------------
    int insMemoryRoomInvite(InsRoomInviteProcDto dto);

}