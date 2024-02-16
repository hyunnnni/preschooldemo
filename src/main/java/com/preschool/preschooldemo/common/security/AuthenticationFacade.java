package com.preschool.preschooldemo.common.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationFacade {
    public MyUserDetails getLoginUser(){

        return SecurityContextHolder
                .getContext() == null
                ? null
                : (MyUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication() //토큰값이 넘어옴  //null이면 로그인 x, null이 아니면 로그인 상태
                .getPrincipal(); //오브젝트에서 형변환
    }

    public int getLoginUserPk(){
        MyUserDetails myUserDetails = getLoginUser();
        return myUserDetails == null
                ? 0
                : myUserDetails
                .getMyPrincipal()
                .getIuser();
    }

    public int getLevelPk(){
        MyUserDetails myUserDetails = getLoginUser();
        return myUserDetails == null
                ? 0
                : myUserDetails
                .getMyPrincipal()
                .getIlevel();
    }


    public List<String> getRoles(){
        MyUserDetails myUserDetails = getLoginUser();
        return myUserDetails == null
                ? null
                : myUserDetails
                .getMyPrincipal()
                .getRoles();
    }
}
