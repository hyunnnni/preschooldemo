package com.preschool.preschooldemo.kid;

import com.preschool.preschooldemo.kid.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KidMapper {
    //원아 정보 출력
    KidProfileVo kidProfile(int ikid);
    List<KidGrowth> kidGrowth(int ikid, int year);
    List<KidParent> kidParent(int ikid);
    //원아 코드 수정
    int kidCode(int ikid);
    //원아 코드 중복 체크
    String kidAllCode(KidCode dto);
    List<String> kidAllCode1();
    //원아등록
    String code();
    int kidSignup(KidInsDto dto);
    int kidUpdPic(KidInsDto dto);
    int kidgrade(KidInsDto dto);
    //원아 발달사항 등록
    Integer growthCheck(GrowthCheck dto);
    Integer bodyCheck(GrowthCheck dto);
    int kidGrowthInsDetail(KidDetailInsDto dto);
    int kidBodyInsDetail(KidDetailInsDto dto);
    //원아 발달사항 수정 시 기존 정보 출력
    KidDetailEditVo kidDetailEdit(int ikid);
    //원아 발달사항 수정
    int kidGrowthUpdDetail(KidDetailUpdDto dto);
    int kidBodyUpdDetail(KidDetailUpdDto dto);
    //원아정보 수정
    int kidUpdProfile(KidUpdDto dto);
    //원아 수정 시 기존 정보 출력
    KidProfileEditVo kidEdit(int ikid);
    //원아 해당년도 모두 졸업
    int allGraduateKid();
    int allGraduateDelKid();
    //------------------------ 졸업, 퇴소 원아 부모 연결 끊고 삭제 ---------------------
    List<Integer> selKidPk();
    List<Integer> selParentPk(List<Integer> ikids);
    List<Integer> selKidParentPk(int iparent);
    int delParentKid(List<Integer> ikids);
    int delParent(int iparent);

}
