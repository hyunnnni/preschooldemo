package com.preschool.preschooldemo.teacher;

import com.preschool.preschooldemo.common.exception.*;
import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.security.JwtTokenProvider;
import com.preschool.preschooldemo.common.security.MyPrincipal;
import com.preschool.preschooldemo.common.security.MyUserDetails;
import com.preschool.preschooldemo.common.utils.*;
import com.preschool.preschooldemo.parent.model.*;
import com.preschool.preschooldemo.teacher.model.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookieUtils;

    //-------------------------------- 원아 관리 페이지 조회 --------------------------------
    public SelKidManagementVo getKidManagement(SelKidManagementDto dto) {

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            throw new RestApiException(AuthErrorCode.NO_PERMISSION);
        }

        SelKidManagementVo vo = new SelKidManagementVo();
        List<SelKidManagement> voList = new ArrayList<>();

        voList = mapper.selKidManagement(dto);

        if (voList.size() == 0) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        vo.setKidPage(voList);
        vo.setTotalCnt(mapper.selKidPage(dto));

        return vo;
    }

    //-------------------------------- 원아 재원 상태 / 반 승급 수정 --------------------------------
    @Transactional
    public ResVo patchKidStateOrClass(UpdKidStateDto dto) {

        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
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
                    int updResult = mapper.updStateIsDelParent(pDto);
                    if (updResult == Const.ZERO) {
                        throw new RestApiException(AuthErrorCode.UPD_IS_DEL_FAIL);
                    }
                }
            }
        }

        if (dto.getKidCheck() >= Const.CLASS_HIBISCUS && dto.getKidCheck() <= Const.CLASS_ROSE) {
            InsKidManagementProc pdto = InsKidManagementProc.builder()
                    .ikids(dto.getIkids())
                    .iclass(dto.getKidCheck())
                    .build();
            int insResult = mapper.insClass(pdto);
            if (insResult == 0) {
                throw new RestApiException(AuthErrorCode.GRADE_FAIL);
            }
        }
        if (result > Const.ZERO) {
            return new ResVo(result);
        }
        if (result == Const.ZERO) {
            throw new RestApiException(AuthErrorCode.UPD_STATE_FAIL);
        }
        return new ResVo(Const.FAIL);
    }

    //-------------------------------- 학부모 관리 페이지 조회 --------------------------------
    public SelParManagementVo getParentManagement(SelParManagementDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            throw new RestApiException(AuthErrorCode.NO_PERMISSION);
        }

        List<SelParManagement> voList = new ArrayList<>();
        SelParManagementVo vo = new SelParManagementVo();
        Integer total = Const.ZERO;

        if (dto.getIclass() > 0) {
            voList = mapper.selParManagementClass(dto);
            total = mapper.selParPageClass(dto);

        }
        if (dto.getIclass() == 0) {
            voList = mapper.selParManagement(dto);
            total = mapper.selParPage(dto.getPrIsDel());
        }

        if (voList.size() == 0) {
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        for (SelParManagement vo1 : voList) {
            List<SelKidNameClass> kids = mapper.selConnectionKid(vo1.getIparent());
            if (kids.size() > 0) {
                vo1.setKids(kids);
            }
        }
        vo.setTotalCnt(total);
        vo.setParentPage(voList);

        return vo;
    }

    //-------------------------------- 학부모 정보 관리자가 삭제 --------------------------------
    @Transactional
    public ResVo delParent(DelParentDto dto) {
        int level = authenticationFacade.getLevelPk();
        dto.setIlevel(level);

        if (!(dto.getIlevel() == Const.TEACHER || dto.getIlevel() == Const.BOSS)) {
            throw new RestApiException(AuthErrorCode.NO_PERMISSION);
        }

        int delResult = mapper.delParDisconnectKid(dto.getIparents());

        int isDelResult = mapper.updIsDelParent(dto);
        if (isDelResult == 0) {
            throw new RestApiException(AuthErrorCode.UPD_IS_DEL_FAIL);
        }

        return new ResVo(isDelResult);
    }

//-------------------------------- 학부모와 원아 연결 끊기  --------------------------------

    public ResVo delDisconnect(DelDisconnectDto dto) {

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

    }

    //-------------------------------- 선생님 정보 수정 시 불러오기 --------------------------------

    public TeacherEditVo selTeacherEdit(int iteacher) {
        int level = authenticationFacade.getLevelPk();
        if (level < Const.TEACHER) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try {
            return mapper.selTeacherEdit(iteacher);
        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 띄우기
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //-------------------------------- 선생님 정보 수정  --------------------------------

    public ResVo putTeacher(MultipartFile teacherProfile, TeacherPatchDto dto) {

        int level = authenticationFacade.getLevelPk();
        dto.setLoginIlevel(level);

        if (level < Const.TEACHER) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        if (teacherProfile == null) {
            throw new RestApiException(AuthErrorCode.PICS_NULL);
        }
        try {
            String path = "/user/" + dto.getIteacher();
            myFileUtils.delFolderTrigger(path);
            String savedFileNm = myFileUtils.transferTo(teacherProfile, path);
            dto.setTeacherProfile(savedFileNm);

            int affectedRows = mapper.updTeacher(dto);

            if (affectedRows > 0) {
                return new ResVo(Const.SUCCESS);
            }

        } catch (Exception e) {
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(Const.SUCCESS);
    }

    //-------------------------------- 선생님 정보 삭제 --------------------------------
    public ResVo delTeacher(TeacherDelDto dto) {
        int level = authenticationFacade.getLevelPk();
        if (level != Const.BOSS) {
            throw new RestApiException(PreschoolErrorCode.ACCESS_RESTRICTIONS);
        }
        try{
            int affectedRows = mapper.updIsDelTeacher(dto);
            if (affectedRows > 0) {
                return new ResVo(Const.SUCCESS);
            }
        } catch (Exception e) {
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(Const.FAIL);
    }

    //-------------------------------- 선생님 로그인 --------------------------------

    public TeacherEntity teacherSignin(HttpServletRequest req, HttpServletResponse res, TeacherSigninDto dto) {
        TeacherEntity entity = mapper.selTeacher(dto);

        String upw = mapper.checkTeacherInfo(dto.getTeacherUid());

        if (upw == null) {
            throw new RestApiException(AuthErrorCode.NOT_EXIST_USER_ID);

        } else if (!dto.getTeacherUpw().equals(upw)) {
            throw new RestApiException(AuthErrorCode.VALID_PASSWORD);
        }
        if (entity.getTcIsDel() == 1) {
            throw new RestApiException(AuthErrorCode.DELETE_ID);
        }

        MyPrincipal myPrincipal = MyPrincipal.builder()
                .iuser(entity.getIteacher())
                .ilevel(entity.getIlevel())
                .build();

        myPrincipal.getRoles().add(entity.getRole());

        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        String rt = jwtTokenProvider.generateRefreshToken(myPrincipal);

        int rtCookieMaxAge = appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);

        HttpSession session = req.getSession(true);
        session.setAttribute("loginUserPk", entity.getIteacher());

        entity.setAccessToken(at);

        return entity;
    }

    //-------------------------------- 선생님이 부모 마이페이지 정보수정 --------------------------------
    public ResVo putTeacherParent(UpdTeacherParentDto dto) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        if (dto.getParentNm() == null && dto.getPhoneNb() == null && dto.getPrEmail() == null
                && dto.getUpw() == null) {
            return new ResVo(-1);

        }
        int result1 = mapper.putTeacherParent(dto);
        if (result1 == 0) {
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
        return new ResVo(1);

    }

    //-------------------------------- 부모 원래정보 불러오기 --------------------------------
    public TeacherParentBeforInfoVo getTeacherParentEdit(int iparent) {
        int level = authenticationFacade.getLevelPk();
        if (level < 2) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        TeacherParentBeforInfoVo vo = mapper.selBeforeInfo1(iparent);
        return vo;
    }

    //----------------------------- 3차 원장님이 선생님 등록하기 ------------------
    public ResVo insTeacher(MultipartFile pic, TeacherInsDto dto) {
        int result = mapper.insTeacher(dto);
        if (result == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        String path = "/teacher/" + dto.getIteacher();
        String savedPicFileNm = myFileUtils.transferTo(pic, path);
        dto.setTeacherProfile(savedPicFileNm);
        int result2 = mapper.teacherUpPic(dto);
        if (result2 == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }
        return new ResVo(dto.getIteacher());
    }

    //---------------------------- 3차 선생님 전체 or 선택조회 ---------------------
    public List<SelAllTeacherVo> selAllTeacher(SelAllTeacherDto dto) {
        if (dto.getPage() == 0) {
            throw new RestApiException(AuthErrorCode.FAIL);
        }

        return mapper.selAllTeacher(dto);
    }
    //-------------------------------- 리프레시 토큰 --------------------------------
    public TeacherEntity getRefreshToken(HttpServletRequest req) {//at를 다시 만들어줌
        Cookie cookie = cookieUtils.getCookie(req, "rt");
        TeacherEntity vo = new TeacherEntity();
        if (cookie == null) {
            vo.setAccessToken(null);
            return vo;
        }
        String token = cookie.getValue();
        if (!jwtTokenProvider.isValidateToken(token)) {
            vo.setAccessToken(null);
            return vo;
        }
        MyUserDetails myUserDetails = (MyUserDetails) jwtTokenProvider.getUserDetailsFromToken(token);
        MyPrincipal myPrincipal = myUserDetails.getMyPrincipal();
        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        vo.setAccessToken(at);
        return vo;
    }
    //-------------------------------- 파이어베이스 토큰 --------------------------------
    public ResVo patchTeaFirebaseToken(UserFirebaseTokenPatchDto dto) {
        int affectedRows = mapper.updTeaFirebaseToken(dto);
        return new ResVo(affectedRows);
    }
}