package teacher;

import common.Const;
import common.ResVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teacher.model.SelKidManagementDto;
import teacher.model.SelKidManagementVo;
import teacher.model.UpdKidStateDto;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherMapper mapper;

//-------------------------------- 원아 관리 페이지 조회 --------------------------------
    public List<SelKidManagementVo> getKidManagement(SelKidManagementDto dto){

        try{
            List<SelKidManagementVo> voList = new ArrayList<>();
            SelKidManagementVo vo = new SelKidManagementVo();

            if(dto.getIrank() != Const.TEACHER || dto.getIrank() != Const.BOSS){
                vo.setResult(Const.NO_PERMISSION);
                voList.add(vo);
                return voList;
            }

            voList = mapper.selKidManagement(dto);

            if(voList.size() > 0){
                return voList;
            }

            vo.setResult(Const.FAIL);
            voList.add(vo);
            return voList;

        }catch (Exception e){
            return null;
        }
    }
//-------------------------------- 원아 재원 상태 수정 --------------------------------
    public ResVo patchKidState (UpdKidStateDto dto){

        try {
            if (dto.getIrank() != Const.TEACHER || dto.getIrank() != Const.BOSS) {
                return new ResVo(Const.NO_PERMISSION);
            }

            int result = mapper.updKidState(dto);

            if (result == 0) {
                result = Const.FAIL;
            }
            return new ResVo(result);

        }catch (Exception e){
            return new ResVo(Const.INTERNAL_SERVER_ERROR);
        }

    }
}
