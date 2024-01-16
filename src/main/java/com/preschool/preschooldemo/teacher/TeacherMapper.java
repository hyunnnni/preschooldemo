package com.preschool.preschooldemo.teacher;

import com.preschool.preschooldemo.teacher.model.UpdKidStateParentProc;
import org.apache.ibatis.annotations.Mapper;
import com.preschool.preschooldemo.teacher.model.SelKidManagementDto;
import com.preschool.preschooldemo.teacher.model.SelKidManagementVo;
import com.preschool.preschooldemo.teacher.model.UpdKidStateDto;

import java.util.List;

@Mapper
public interface TeacherMapper {

    List<SelKidManagementVo> selKidManagement(SelKidManagementDto dto);

    int updKidStateOrClass(UpdKidStateDto dto);
    List<Integer> SelGraduateParentPk(List<Integer> ikids);
    List<Integer> SelGraduateKidPk(int iparent);
    List<Integer> selStateKid(List<Integer> ikids);
    int updGraduateKidParent(UpdKidStateParentProc pDto);
}
