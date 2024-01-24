package com.preschool.preschooldemo.teacher;

import com.preschool.preschooldemo.common.ResVo;
import com.preschool.preschooldemo.common.Const;
import com.preschool.preschooldemo.exception.AuthErrorCode;
import com.preschool.preschooldemo.exception.CommonErrorCode;
import com.preschool.preschooldemo.exception.RestApiException;
import com.preschool.preschooldemo.noticeboard.model.InsKidManagementProc;
import com.preschool.preschooldemo.security.AuthenticationFacade;
import com.preschool.preschooldemo.teacher.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherMapper mapper;
    private final AuthenticationFacade authenticationFacade;

//-------------------------------- 원아 관리 페이지 조회 --------------------------------
    public List<SelKidManagementVo> getKidManagement(SelKidManagementDto dto){

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        try{

            if(!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)){
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            List<SelKidManagementVo> voList = new ArrayList<>();
            SelKidManagementVo vo = new SelKidManagementVo();

            voList = mapper.selKidManagement(dto);

            if(voList.size() == 0){
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            return voList;

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }


    }
//-------------------------------- 원아 재원 상태 / 반 승급 수정 --------------------------------
    public ResVo patchKidStateOrClass (UpdKidStateDto dto){
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        try {

            if(!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)){
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            int result = mapper.updKidStateOrClass(dto);
            DelStateParentIsProc pDto = new DelStateParentIsProc();

            if (dto.getKidCheck() == Const.STATE_GRADUATE || dto.getKidCheck() == Const.STATE_DROP_OUT) {

                List<Integer> iParents = mapper.SelGraduateParentPk(dto.getIkids());

                if (iParents.size() != 0) {
                    for (int parent : iParents) {

                        List<Integer> ikids = mapper.SelGraduateKidPk(parent);
                        List<Integer> state = mapper.selStateKid(ikids);

                        if (state.contains(Const.ZERO)) {
                            continue;
                        }
                        pDto.setIparent(parent);
                        int delResult = mapper.updStateIsDelParent(pDto);
                        if (delResult == Const.ZERO) {
                            throw new RestApiException(AuthErrorCode.UPD_IS_DEL_FAIL);
                        }
                    }
                }
            }
            if(dto.getKidCheck() >= Const.CLASS_HIBISCUS || dto.getKidCheck() <= Const.CLASS_ROSE){
                InsKidManagementProc pdto = InsKidManagementProc.builder()
                        .ikids(dto.getIkids())
                        .iclass(dto.getKidCheck())
                        .build();
                int insResult = mapper.insClass(pdto);
                if(insResult == 0){
                    throw new RestApiException(AuthErrorCode.GRADE_FAIL);
                }
            }
            if (result > Const.SUCCESS || result > Const.ZERO) {
                return new ResVo(result);
            }
            if (result == Const.ZERO) {
                throw new RestApiException(AuthErrorCode.UPD_STATE_FAIL);
            }
            return new ResVo(Const.FAIL);

        }catch (Exception e){

            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);

        }
    }
//-------------------------------- 학부모 관리 페이지 조회 --------------------------------
    public List<SelParManagementVo> getParentManagement (SelParManagementDto dto){

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            List<SelParManagementVo> voList = new ArrayList<>();
            SelParManagementVo vo = new SelParManagementVo();

            if (dto.getIclass() > 0) {
                voList = mapper.selParManagementClass(dto);
            } else if (dto.getIclass() == 0) {
                voList = mapper.selParManagement(dto);
            }

            if (voList.size() == 0) {
                throw new RestApiException(AuthErrorCode.NO_INFORMATION);
            }

            for (SelParManagementVo vo1 : voList) {
                List<SelKidNameClass> kids = mapper.selConnectionKid(vo1.getIparent());
                if (kids.size() > 0) {
                    vo1.setKids(kids);
                }
            }

            return voList;

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

//-------------------------------- 학부모 정보 관리자가 삭제 --------------------------------

    public ResVo delParent(DelParentDto dto){

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            int delResult = mapper.delParDisconnectKid(dto.getIparents());
            if (delResult == 0) {
                throw new RestApiException(AuthErrorCode.UPD_IS_DEL_FAIL);
            }
            int isDelResult = mapper.updIsDelParent(dto);
            if (isDelResult == 0) {
                throw new RestApiException(AuthErrorCode.UPD_IS_DEL_FAIL);
            }

            return new ResVo(isDelResult);

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

//-------------------------------- 학부모와 원아 연결 끊기  --------------------------------

    public ResVo delDisconnect(DelDisconnectDto dto) {

        try {
            int level = authenticationFacade.getLevelPk();
            dto.setIlevel(level);

            if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
                throw new RestApiException(AuthErrorCode.NO_PERMISSION);
            }

            int result = mapper.delDisconnent(dto);

            if (result == 0) {
                throw new RestApiException(AuthErrorCode.FAIL);
            }

            return new ResVo(result);

        }catch (Exception e){
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }


}
