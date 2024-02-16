package com.preschool.preschooldemo.parent;

import com.preschool.preschooldemo.parent.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParentMapper {
    //-------------------------------- 식별코드셀렉 --------------------------------
    CodeVo selCode(CodeDto dto);
    //-------------------------------- 로그인시 애 가져오기 --------------------------------
    List<SelKid> selKid(int iparent);
    //-------------------------------- 아이디 중복체크 --------------------------------
    String checkParentInfo(String uid);
    //-------------------------------- 부모 회원가입 --------------------------------
    int insParent(ParentInsDto dto);
    //-------------------------------- 원아와 연결된 부모 pk --------------------------------
    List<Integer> connectParent(int iparent);
    //-------------------------------- 부모님 로그인 성공 시 정보 조회 --------------------------------
    ParentEntity checkParentsId(ParentSigninDto dto);
    //-------------------------------- 마이페이지 아이 추가 --------------------------------
    int insParentKidTable(ParentKid dto);
    //-------------------------------- 원래정보 불러오기 --------------------------------
    ParentBeforInfoVo selBeforeInfo(int iparent);
    //-------------------------------- 마이페이지 정보수정 --------------------------------
    int putParent(UpParentDto dto);
    //-------------------------------- 부모 탈퇴 --------------------------------
    int delParent(int loginUserPk);
    int delParentkid(int loginUserPk);
    //-------------------------------- 토큰 --------------------------------
    int updParFirebaseToken(UserFirebaseTokenPatchDto dto);
    //-------------------------------- 해당 부모와 원아가 연결 되어 있는지 체크 --------------------------------
    Integer selKidParent(int ikid, int iparent);


}
