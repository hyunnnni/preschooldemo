package com.preschool.preschooldemo.kid;

import com.preschool.preschooldemo.common.exception.AuthErrorCode;
import com.preschool.preschooldemo.common.exception.CommonErrorCode;
import com.preschool.preschooldemo.common.exception.RestApiException;
import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.utils.Const;
import com.preschool.preschooldemo.common.utils.MyFileUtils;
import com.preschool.preschooldemo.common.utils.ResVo;
import com.preschool.preschooldemo.kid.model.*;
import com.preschool.preschooldemo.kid.model.KidDetailEditVo;
import com.preschool.preschooldemo.kid.model.KidGrowth;
import com.preschool.preschooldemo.kid.model.KidParent;
import com.preschool.preschooldemo.kid.model.KidProfileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KidService {
    private final KidMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    //-------------------------------- 원아 해당 년도 마이페이지 조회 --------------------------------
    public KidProfileVo kidProfile(int year, int ikid) {
        int level = authenticationFacade.getLevelPk();
        if (level < 1) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        KidProfileVo vo = mapper.kidProfile(ikid);
        if (vo == null) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }
        vo.setResult(Const.SUCCESS);
            List<KidParent> parents = mapper.kidParent(ikid);
            vo.setParents(parents);
            if(parents == null){
                throw new RestApiException(AuthErrorCode.FAIL);
            }
            List<KidGrowth> growths = mapper.kidGrowth(ikid, year);
            vo.setGrowths(growths);
        if(growths == null){
            throw new RestApiException(AuthErrorCode.FAIL);
        }
            return vo;
    }

    //-------------------------------- 원아 식별코드 수정 --------------------------------
    ResVo kidCode(int ikid) {
        try {
            List<String> codes = mapper.kidAllCode1();
            mapper.kidCode(ikid);
            KidCode ikid1 = new KidCode();
            ikid1.setIkid(ikid);
            String code1 = mapper.kidAllCode(ikid1);
            for (int i = 0; i < codes.size(); i++) {
                if(codes.get(i).equals(code1)){
                    mapper.kidCode(ikid);
                    ikid1.setIkid(ikid);
                    code1 = mapper.kidAllCode(ikid1);
                    i = 0;
                }
            }
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //-------------------------------- 원아 등록 --------------------------------
    @Transactional
    public KidInsVo kidSignup(MultipartFile pic, KidInsDto dto) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }

        String code = mapper.code();
        KidCode code1 = new KidCode();
        code1.setCode(code);
        String codes = mapper.kidAllCode(code1);

        do {
            if(codes == null){
                dto.setCode(code);
            } else {
                code = mapper.code();
                code1.setCode(code);
                codes = mapper.kidAllCode(code1);
                dto.setCode(code);
            }
        } while (codes == null);

        int result = mapper.kidSignup(dto);
        if (result == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        String path = "/kid/" + dto.getIkid();
        String savedPicFileNm = myFileUtils.transferTo(pic, path);
        dto.setProfile(savedPicFileNm);
        int result2 = mapper.kidUpdPic(dto);
        if (result2 == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        int grade = mapper.kidgrade(dto);
        if (grade == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        KidInsVo vo2 = new KidInsVo();
        vo2.setCode(dto.getCode());
        vo2.setIkid(dto.getIkid());
        return vo2;

    }

    //-------------------------------- 원아 발달사항 등록 --------------------------------
    @Transactional
    public ResVo kidInsDetail(List<KidDetailInsDto> list) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        GrowthCheck vo = new GrowthCheck();
        for (KidDetailInsDto dto : list) {
            if (dto.getGrowthDate() != null) {
                if (dto.getGrowth() < 1 || dto.getGrowth() > 10) {
                    throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
                }
                int growthmonth = Integer.parseInt(dto.getGrowthDate().substring(5, 7));
                switch (growthmonth / 3) {
                    case 0: dto.setGrowthQuarterly(4); break;
                    case 1: dto.setGrowthQuarterly(1); break;
                    case 2: dto.setGrowthQuarterly(2); break;
                    case 3: dto.setGrowthQuarterly(3); break;
                    case 4: dto.setGrowthQuarterly(4); break;
                }
                vo.setGrowthQuarterly(dto.getGrowthQuarterly());
                vo.setIkid(dto.getIkid());
                vo.setGrowthDate(dto.getGrowthDate());
                Integer check = mapper.growthCheck(vo);
                log.info("check:{}", check);
                if (check != null) {
                    throw new RestApiException(AuthErrorCode.OVER_GROWTH);
                }

                int kiddetail1 = mapper.kidGrowthInsDetail(dto);
                if (kiddetail1 == 0) {
                    throw new RestApiException(AuthErrorCode.FAIL);
                }
            }
            if (dto.getBodyDate() != null) {
                if (dto.getHeight() < 1 || dto.getWeight() < 1) {
                    throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
                }
                int bodymonth = Integer.parseInt(dto.getBodyDate().substring(5, 7));
                switch (bodymonth / 3) {
                    case 0: dto.setBodyQuarterly(4); break;
                    case 1: dto.setBodyQuarterly(1); break;
                    case 2: dto.setBodyQuarterly(2); break;
                    case 3: dto.setBodyQuarterly(3); break;
                    case 4: dto.setBodyQuarterly(4); break;
                }
                vo.setBodyQuarterly(dto.getBodyQuarterly());
                vo.setIkid(dto.getIkid());
                vo.setBodyDate(dto.getBodyDate());
                Integer check = mapper.bodyCheck(vo);
                if (check != null) {
                    throw new RestApiException(AuthErrorCode.OVER_GROWTH);
                }
                int kiddetail2 = mapper.kidBodyInsDetail(dto);
                if (kiddetail2 == 0) {
                    throw new RestApiException(AuthErrorCode.FAIL);
                }
            }
        }
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------- 원아 발달사항 수정 --------------------------------
    @Transactional
    public ResVo kidUpdDetail(List<KidDetailUpdDto> list) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        for (KidDetailUpdDto dto : list) {
            if (dto.getGrowthDate() != null) {
                if (dto.getGrowth() < 1 || dto.getGrowth() > 10) {
                    throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
                }
                int growthmonth = Integer.parseInt(dto.getGrowthDate().substring(5, 7));
                switch (growthmonth / 3) {
                    case 0: dto.setGrowthQuarterly(4); break;
                    case 1: dto.setGrowthQuarterly(1); break;
                    case 2: dto.setGrowthQuarterly(2); break;
                    case 3: dto.setGrowthQuarterly(3); break;
                    case 4: dto.setGrowthQuarterly(4); break;
                }

                int kiddetail1 = mapper.kidGrowthUpdDetail(dto);
                if (kiddetail1 == 0) {
                    KidDetailInsDto insDto = new KidDetailInsDto();
                    insDto.setIkid(dto.getIkid());
                    insDto.setGrowth(dto.getGrowth());
                    insDto.setGrowthDate(dto.getGrowthDate());
                    insDto.setGrowthMemo(dto.getGrowthMemo());
                    insDto.setGrowthQuarterly(dto.getGrowthQuarterly());
                    int kiddetail11 = mapper.kidGrowthInsDetail(insDto);
                    if (kiddetail11 == 0) {
                        throw new RestApiException(AuthErrorCode.FAIL);
                    }
                }
            }
            if (dto.getBodyDate() != null) {
                if (dto.getHeight() < 1 || dto.getWeight() < 1) {
                    throw new RestApiException(AuthErrorCode.NOT_EMPTY_INFO);
                }
                int bodymonth = Integer.parseInt(dto.getBodyDate().substring(5, 7));
                switch (bodymonth / 3) {
                    case 0: dto.setBodyQuarterly(4); break;
                    case 1: dto.setBodyQuarterly(1); break;
                    case 2: dto.setBodyQuarterly(2); break;
                    case 3: dto.setBodyQuarterly(3); break;
                    case 4: dto.setBodyQuarterly(4); break;
                }
                int kiddetail2 = mapper.kidBodyUpdDetail(dto);
                if (kiddetail2 == 0) {
                    KidDetailInsDto insDto = new KidDetailInsDto();
                    insDto.setIkid(dto.getIkid());
                    insDto.setWeight(dto.getWeight());
                    insDto.setHeight(dto.getHeight());
                    insDto.setBodyDate(dto.getBodyDate());
                    insDto.setBodyQuarterly(dto.getBodyQuarterly());
                    int kiddetail22 = mapper.kidBodyInsDetail(insDto);
                    if (kiddetail22 == 0) {
                        throw new RestApiException(AuthErrorCode.FAIL);
                    }
                }
            }
        }
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------- 원아 발달사항 수정 시 기존 데이터 조회 --------------------------------
    public KidDetailEditVo kidDetailEdit(int ikid, int year) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        KidDetailEditVo vo = mapper.kidDetailEdit(ikid);
        if (vo == null) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }

        List<KidGrowth> growths = mapper.kidGrowth(ikid, year);
        if (growths == null) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        vo.setGrowths(growths);
        vo.setResult(Const.SUCCESS);
        return vo;
    }

    //-------------------------------- 원아 프로필 수정 --------------------------------
    public ResVo kidUpdProfile(MultipartFile pic, KidUpdDto dto) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
            String path = "/kid/" + dto.getIkid();
            myFileUtils.delFolderTrigger(path);
            String savedPicFileNm = myFileUtils.transferTo(pic, path);
            dto.setProfile(savedPicFileNm);
        int kid = mapper.kidUpdProfile(dto);
        if (kid == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
            return new ResVo(Const.SUCCESS);
        }

    //-------------------------------- 원아 프로필 수정 시 기존 데이터 조회 --------------------------------
    public KidProfileEditVo kidEdit(int ikid) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
            KidProfileEditVo vo = mapper.kidEdit(ikid);
        if (vo == null) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        vo.setResult(Const.SUCCESS);
        return vo;
    }

    //-------------------------------- 졸업한 지 10년 된  원아 전체 삭제 --------------------------------
    @Transactional
    public ResVo allGraduateKid() {
        int level = authenticationFacade.getLevelPk();
        if (level < 3) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }

        List<Integer> ikids = mapper.selKidPk();
        if (ikids.size() == 0) {
            throw new RestApiException(AuthErrorCode.GRADUATE_FAIL);
        }
        List<Integer> iparents = mapper.selParentPk(ikids);
        if (iparents.size() == 0) {
            throw new RestApiException(AuthErrorCode.GRADUATE_FAIL);
        }
        for (int i = 0; i < ikids.size(); i++) {
            for (int j = 0; j < ikids.size(); j++) {
                if(i!=j){
                    if(ikids.get(i) == ikids.get(j)){
                        ikids.remove(j);
                    }
                }
            }
        }
        int delparentkid = mapper.delParentKid(ikids);
        if (delparentkid == 0) {
            throw new RestApiException(AuthErrorCode.GRADUATE_FAIL);
        }
        for (int i = 0; i < iparents.size(); i++) {
            for (int j = 0; j < iparents.size(); j++) {
                if(i!=j){
                    if(iparents.get(i) == iparents.get(j)){
                        iparents.remove(j);
                    }
                }
            }
        }
        for (int iparent : iparents ) {
            List<Integer> result = mapper.selKidParentPk(iparent);
            if (result == null){
                mapper.delParent(iparent);
            }
        }
        int del1 = mapper.allGraduateKid();
        int del2 = mapper.allGraduateDelKid();
        if (del2 == 0) {
            throw new RestApiException(AuthErrorCode.GRADUATE_FAIL);
        }
            return new ResVo(Const.SUCCESS);
    }
}
