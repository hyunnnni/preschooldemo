package com.preschool.preschooldemo.preschool;

import com.preschool.preschooldemo.preschool.model.KidProfileVo;
import com.preschool.preschooldemo.preschool.model.TeacherProfileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PreschoolMapper {

    List<KidProfileVo> getKidProfile();
    List<TeacherProfileVo> getTeacherProfile();
}
