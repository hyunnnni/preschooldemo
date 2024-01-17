package com.preschool.preschooldemo.teacher;

import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.teacher.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherMapper mapper;

//-------------------------------- 원아 관리 페이지 조회 --------------------------------
    public List<SelKidManagementVo> getKidManagement(SelKidManagementDto dto){

            List<SelKidManagementVo> voList = new ArrayList<>();
            SelKidManagementVo vo = new SelKidManagementVo();

            if(!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)){
                vo.setResult(Const.NO_PERMISSION);
                voList.add(vo);
                return voList;
            }

            voList = mapper.selKidManagement(dto);

            if(voList.size() > 0){
                return voList;
            }

            vo.setResult(Const.NO_INFORMATION);
            voList.add(vo);
            return voList;


    }
//-------------------------------- 원아 재원 상태 / 반 승급 수정 --------------------------------
    public ResVo patchKidStateOrClass (UpdKidStateDto dto){

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                return new ResVo(Const.NO_PERMISSION);
            }

            int result = mapper.updKidStateOrClass(dto);
            UpdKidStateParentProc pDto = new UpdKidStateParentProc();
            int delResult = Const.RESULT;

            if(dto.getKidCheck() == Const.STATE_GRADUATE || dto.getKidCheck() == Const.STATE_DROP_OUT) {

                List<Integer> iParents = mapper.SelGraduateParentPk(dto.getIkids());

                if (iParents.size() != 0) {
                    for (int parent : iParents) {

                        List<Integer> ikids = mapper.SelGraduateKidPk(parent);
                        List<Integer> state = mapper.selStateKid(ikids);

                        if (state.contains(Const.ZERO)) {
                            continue;
                        }
                        pDto.setIparent(parent);
                        pDto.setPrIsDel(Const.FAKE_IS_DEL);
                        delResult = mapper.updGraduateKidParent(pDto);
                        if (delResult == Const.ZERO) {
                            return new ResVo(Const.UPD_IS_DEL_FAIL);
                        }
                    }
                }
            }
            if (result > Const.SUCCESS || result > Const.ZERO) {
                return new ResVo(result);
            }
            if (result == Const.ZERO){
                return new ResVo(Const.UPD_STATE_FAIL);
            }
            return new ResVo(Const.FAIL);
    }
//-------------------------------- 학부모 관리 페이지 조회 --------------------------------
    public List<SelParManagementVo> getParentManagement (SelParManagementDto dto){
        List<SelParManagementVo> voList = new ArrayList<>();
        SelParManagementVo vo = new SelParManagementVo();

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            vo.setResult(Const.NO_PERMISSION);
            voList.add(vo);
            return voList;
        }

        if(dto.getIclass() > 0){
            voList = mapper.selParManagementClass(dto);
        }else if(dto.getIclass() == 0) {
            voList = mapper.selParManagement(dto);
        }

        if(voList.size() == 0){
            vo.setResult(Const.NO_INFORMATION);
            voList.add(vo);
            return voList;
        }

        for(SelParManagementVo vo1 : voList){
            List<SelKidNameClass> kids = mapper.selConnectionKid(vo1.getIparent());
            if(kids.size() > 0) {
                vo1.setKids(kids);
            }
        }

        return voList;
    }
}
