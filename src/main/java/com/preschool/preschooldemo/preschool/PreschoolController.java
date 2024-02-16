package com.preschool.preschooldemo.preschool;

import com.preschool.preschooldemo.common.exception.AuthErrorCode;
import com.preschool.preschooldemo.common.exception.RestApiException;
import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.preschool.model.KidProfileVo;
import com.preschool.preschooldemo.preschool.model.TeacherProfileVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/preschool")
@Tag(name = "유치원 선생님 반 별 원아 불러오기", description = "유치원 현황")
public class PreschoolController {
    private final PreschoolService service;
    private final AuthenticationFacade authenticationFacade;

    @GetMapping("/kid")
    @Operation(summary = "전체 반 원아 불러오기", description = "<strong>반 원아 불러오기</strong><br><br>" +
            "반별 원아 불러오기<br>" +
            "성공시 원아 이름, 원아 사진으로 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public List<KidProfileVo> getKidProfile(){
        int level = authenticationFacade.getLevelPk();
        if(level<1){
            throw new RestApiException(AuthErrorCode.NOT_ENTER_ACCESS);
        }
        return service.getKidProfile();
    }

    @GetMapping("/teacher")
    @Operation(summary = "전체 선생님 불러오기", description = "<strong>전체 선생님 불러오기</strong><br><br>" +
            "전체 선생님 불러오기<br>" +
            "성공시 선생님 이름, 담당 반, 선생님사진, 소개로 응답<br>" +
            "실패시 에러메세지송출 <br>")
    public List<TeacherProfileVo> getTeacherProfile(){

        return service.getTeacherProfile();
    }
}

