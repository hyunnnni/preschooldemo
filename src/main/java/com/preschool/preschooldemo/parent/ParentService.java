package com.preschool.preschooldemo.parent;

import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.security.JwtTokenProvider;
import com.preschool.preschooldemo.common.security.MyPrincipal;
import com.preschool.preschooldemo.common.security.MyUserDetails;
import com.preschool.preschooldemo.common.utils.AppProperties;
import com.preschool.preschooldemo.common.utils.Const;
import com.preschool.preschooldemo.common.utils.CookieUtils;
import com.preschool.preschooldemo.common.utils.ResVo;
import com.preschool.preschooldemo.common.exception.*;
import com.preschool.preschooldemo.parent.model.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ParentService {
    private final ParentMapper mapper;

    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookieUtils;
    private final AuthenticationFacade authenticationFacade;
    private final PasswordEncoder passwordEncoder;

    //-------------------------------- 식별코드 매칭 --------------------------------
    public CodeVo getMatch(String code) {

        CodeDto dto = new CodeDto();
        dto.setCode(code);

        CodeVo vo = mapper.selCode(dto);

        if (vo == null) {
            throw new RestApiException(AuthErrorCode.CHECK_CODE);
        }

        List<Integer> iparent = mapper.connectParent(vo.getIkid());
        if (iparent.size() >= 2) {
            throw new RestApiException(AuthErrorCode.NOT_CONNETCT_KID);
        }
        return vo;

    }
    //-------------------------------- 아이디 중복 체크 --------------------------------

    public CodeCorrect chekUid(String uid) {
        String checkUid = mapper.checkParentInfo(uid);
        CodeCorrect response = new CodeCorrect();

        if (checkUid != null) {
            throw new RestApiException(AuthErrorCode.ALREADY_EXIST_ID);
        }
        if (uid == null) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ID);
        }
        if (checkUid == null) {
            response.setIsValid(Const.SUCCESS); //회원가입 가능
            response.setMessage("사용가능한 아이디");
        }
        return response;
    }
    //-------------------------------- 부모회원가입 --------------------------------

    @Transactional
    public ResVo insParent(ParentInsDto dto) {

        if (dto.getIsValid() != 1) {
            throw new RestApiException(AuthErrorCode.CHECK_DUPLICATION_ID);
        }
        String checkParent = mapper.checkParentInfo(dto.getUid());
 //       if(checkUid != null){
//            throw new RestApiException(AuthErrorCode.ALREADY_EXIST_ID);
//        }

        if (checkParent == null) {
            mapper.insParent(dto);
            ParentKid pk = new ParentKid();
            pk.setIkid(dto.getIkid());
            pk.setIparent(dto.getIparent());
            mapper.insParentKidTable(pk);

            return new ResVo(1);
        } else {
            // 데이터베이스 삽입이 실패한 경우
            throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
    //-------------------------------- 부모님 로그인 --------------------------------
    public ParentKid parentSignin(HttpServletRequest req, HttpServletResponse res, ParentSigninDto dto) {
        ParentEntity entity = mapper.checkParentsId(dto);
        String upw = mapper.checkParentInfo(dto.getUid());
        if (upw == null) {
            throw new RestApiException(AuthErrorCode.NOT_EXIST_USER_ID);
        } else if (!dto.getUpw().equals(upw)) {
            throw new RestApiException(AuthErrorCode.VALID_PASSWORD);
        }
        if(entity.getPrIsDel() == 1){
            throw new RestApiException(AuthErrorCode.DELETE_ID);
        }
        ParentKid pk = new ParentKid();
        dto.setIparent(entity.getIparent());
        pk.setKidList(mapper.selKid(dto.getIparent()));

        if (dto.getUid() != null && dto.getUpw() != null && dto.getUpw().equals(entity.getUpw())) {
            pk.setIparent(entity.getIparent());
//            pk.setKidList(entity.getIkid());

        }
        MyPrincipal myPrincipal = MyPrincipal.builder()
                .iuser(entity.getIparent())
                .ilevel(entity.getIlevel())
                .build();

        myPrincipal.getRoles().add(entity.getRole());

        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        String rt = jwtTokenProvider.generateRefreshToken(myPrincipal);

        int rtCookieMaxAge = appProperties.getJwt().getRefreshTokenCookieMaxAge();
        cookieUtils.deleteCookie(res, "rt");
        cookieUtils.setCookie(res, "rt", rt, rtCookieMaxAge);

        HttpSession session = req.getSession(true);
        session.setAttribute("loginUserPk", entity.getIparent());

        pk.setAccessToken(at);
        pk.setResult(Const.SUCCESS);
        return pk;
    }
    //-------------------------------- 수정 시 기존 정보 불러오기 --------------------------------
    public ParentBeforInfoVo getParentEdit() {
        int level = authenticationFacade.getLevelPk();
        int iparent = authenticationFacade.getLoginUserPk();
        if (level != 1) {
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        ParentBeforInfoVo vo = mapper.selBeforeInfo(iparent);
        return vo;
    }

    //-------------------------------- 부모 마이페이지 정보수정 --------------------------------
    public ResVo putParent(UpParentDto dto) {

        int loginUserPk = authenticationFacade.getLoginUserPk();
        int level = authenticationFacade.getLevelPk();
        dto.setIparent(loginUserPk);
        dto.setIlevel(level);

        int result = mapper.putParent(dto);

        if (result == 0) {
            return new ResVo(-1);
            //throw new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }

        return new ResVo(1);

    }
    //-------------------------------- 마이페이지 원아추가 --------------------------------
    @Transactional
    public CodeVo postKidCode(CodeDto dto) {

        int loginUserPk = authenticationFacade.getLoginUserPk();
        dto.setIparent(loginUserPk);

        CodeVo vo = mapper.selCode(dto);
        if(vo == null){
            throw new RestApiException(AuthErrorCode.NO_INFORMATION);
        }

        Integer exist = mapper.selKidParent(vo.getIkid(),dto.getIparent());
        if(exist != null){
            throw new RestApiException(AuthErrorCode.ALREADY_CONNECTION);
        }

        List<Integer> iparent = mapper.connectParent(vo.getIkid());

        if (iparent.size() > 2) {
            throw new RestApiException(AuthErrorCode.NOT_CONNETCT_KID);
        } else {
            ParentKid pk = new ParentKid();
            pk.setIkid(vo.getIkid());
            pk.setIparent(dto.getIparent());
            mapper.insParentKidTable(pk);
            return vo;
        }
    }
    //-------------------------------- 부모 정보 삭제 --------------------------------
    public ResVo delParentSelf() {
        int loginUserPk = authenticationFacade.getLoginUserPk();

        int delete = mapper.delParent(loginUserPk);
        if (delete == 0) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        int delete1 = mapper.delParentkid(loginUserPk);
        if (delete1 == 0) {
            throw new RestApiException(AuthErrorCode.NOT_CORRECT_INFORMATION);
        }
        return new ResVo(Const.SUCCESS);
    }
    //-------------------------------- 리프레시 토큰 --------------------------------
    public ParentKid getRefreshToken(HttpServletRequest req) {//at를 다시 만들어줌
        Cookie cookie = cookieUtils.getCookie(req, "rt");
        ParentKid vo = new ParentKid();
        if (cookie == null) {
            vo.setResult(Const.FAIL);
            vo.setAccessToken(null);
            return vo;
        }
        String token = cookie.getValue();
        if (!jwtTokenProvider.isValidateToken(token)) {
            vo.setResult(Const.FAIL);
            vo.setAccessToken(null);
            return vo;
        }
        MyUserDetails myUserDetails = (MyUserDetails) jwtTokenProvider.getUserDetailsFromToken(token);
        MyPrincipal myPrincipal = myUserDetails.getMyPrincipal();
        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        vo.setResult(Const.SUCCESS);
        vo.setAccessToken(at);
        return vo;
    }
    //-------------------------------- 파이어베이스 토큰 --------------------------------
    public ResVo patchParFirebaseToken(UserFirebaseTokenPatchDto dto) {
        int affectedRows = mapper.updParFirebaseToken(dto);
        return new ResVo(affectedRows);
    }
}














