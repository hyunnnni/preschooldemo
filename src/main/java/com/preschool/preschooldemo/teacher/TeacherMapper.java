package com.preschool.preschooldemo.teacher;

import com.preschool.preschooldemo.parent.model.UserFirebaseTokenPatchDto;
import com.preschool.preschooldemo.teacher.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    //------------------------ 원아 관리 페이지 조회 ------------------------
    List<SelKidManagement> selKidManagement(SelKidManagementDto dto);
    //------------------------ 원아 관리 페이지 수 체크 ------------------------
    int selKidPage(SelKidManagementDto dto);
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
    List<SelParManagement> selParManagement(SelParManagementDto dto);
    //------------------------ 현재 계정이 있는 모든 부모의 정보 조회 페이지 수  ------------------------
    Integer selParPage(int prIsDel);
    //------------------------ 원아 반 승급 시 데이터 저장  ------------------------
    int insClass(InsKidManagementProc pdto);
    //------------------------ 부모 정보 원아 반 별 기준으로 조회  ------------------------
    List<SelParManagement > selParManagementClass (SelParManagementDto dto);
    //------------------------ 부모 정보 원아 반 별 기준으로 조회 페이지 수 ------------------------
    Integer selParPageClass(SelParManagementDto dto);
    //------------------------ 해당 부모와 연결이 되어 있는 원아의 이름과 반 조회 ------------------------
    List<SelKidNameClass> selConnectionKid(int iparent);
    //------------------------ 해당 부모와 연결된 원아들과 연결 끊기 ------------------------
    int delParDisconnectKid(List<Integer> iparents);
    //------------------------ 부모 계정 삭제 처리 ------------------------
    int updIsDelParent(DelParentDto dto);
    //------------------------ 학부모와 원아 연결 끊기 ------------------------
    int delDisconnent(DelDisconnectDto dto);
    //------------------------ 선생님 정보 수정 전 불러오기 ------------------------
    TeacherEditVo selTeacherEdit (int iteacher);
    //------------------------ 선생님 회원가입 시 아이디 중복 체크 ( 3차에 쓰셈 )------------------------
    int selTeaUid(String teacherUid);
    //------------------------ 선생님 정보 수정 ------------------------
    int updTeacher (TeacherPatchDto dto);
    //------------------------ 선생님 정보 삭제 ------------------------
    int updIsDelTeacher (TeacherDelDto dto);
    //------------------------ 선생님 로그인 성공 시 정보 조회 ------------------------
    TeacherEntity selTeacher(TeacherSigninDto dto);
    //------------------------ 선생님 로그인 시 비밀번호 체크 ------------------------
    String checkTeacherInfo (String uid);

    //------------------------ 선생님이 부모님 정보 변경 ------------------------
    int putTeacherParent(UpdTeacherParentDto dto);
    //------------------------ 선생님이 부모님 원래정보 불러오기 ------------------------
    TeacherParentBeforInfoVo selBeforeInfo1(int iparent);
    //------------------------ 3차 선생님 원장님이 추가하기 --------------------------
    int insTeacher(TeacherInsDto dto);
    int teacherUpPic(TeacherInsDto dto);
    //----------------------- 3차 선생님 전체조회 or 반별 조회 ------------
    List<SelAllTeacherVo> selAllTeacher(SelAllTeacherDto dto);
    //-------------------------------- 토큰 --------------------------------
    int updTeaFirebaseToken(UserFirebaseTokenPatchDto dto);
}
