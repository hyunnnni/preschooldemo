package com.preschool.preschooldemo.teacher;

import com.preschool.preschooldemo.teacher.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
//------------------------ 원아 관리 페이지 조회 ------------------------
    List<SelKidManagementVo> selKidManagement(SelKidManagementDto dto);

//------------------------ 원아 재원 상태 / 반 승급 수정 ------------------------
    int updKidStateOrClass(UpdKidStateDto dto);
//------------------------ 원아 재원 상태 졸업으로 변경 시 부모 계정 삭제 처리에 필요한 부모 pk 조회 ------------------------
    List<Integer> SelGraduateParentPk(List<Integer> ikids);
//------------------------ 부모와 연결된 모든 원아 조회 ------------------------
    List<Integer> SelGraduateKidPk(int iparent);
//------------------------ 원아 재원 상태 조회 ------------------------
    List<Integer> selStateKid(List<Integer> ikids);
//------------------------ 원아 졸업 시 부모 계정 삭제 처리 ------------------------
    int updStateIsDelParent(DelStateParentIsProc pDto);
//------------------------ 현재 계정이 있는 모든 부모의 정보 조회  ------------------------
    List<SelParManagementVo> selParManagement(SelParManagementDto dto);
//------------------------ 부모 정보 원아 반 별 기준으로 조회  ------------------------
    List<SelParManagementVo> selParManagementClass (SelParManagementDto dto);
//------------------------ 해당 부모와 연결이 되어 있는 원아의 이름과 반 조회 ------------------------
    List<SelKidNameClass> selConnectionKid(int iparent);
//------------------------ 해당 부모와 연결된 원아들과 연결 끊기 ------------------------
    int delParDisconnectKid(List<Integer> iparents);
//------------------------ 부모 계정 삭제 처리 ------------------------
    int updIsDelParent(DelParentDto dto);
//------------------------ 학부모와 원아 연결 끊기 ------------------------
    int delDisconnent(DelDisconnectDto dto);

}
