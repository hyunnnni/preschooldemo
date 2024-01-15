package teacher;

import org.apache.ibatis.annotations.Mapper;
import teacher.model.SelKidManagementDto;
import teacher.model.SelKidManagementVo;
import teacher.model.UpdKidStateDto;

import java.util.List;

@Mapper
public interface TeacherMapper {

    List<SelKidManagementVo> selKidManagement(SelKidManagementDto dto);

    int updKidState (UpdKidStateDto dto);
}
