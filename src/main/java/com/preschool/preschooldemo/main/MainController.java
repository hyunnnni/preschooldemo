package com.preschool.preschooldemo.main;

import com.preschool.preschooldemo.main.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/main")
@Tag(name = "메인 화면 API", description = "메인 화면 관련")
public class MainController {
    private final MainService service;

    //-------------------------------------  메인화면 조회 -------------------------------------//
    @Operation(summary = "메인화면 조회")
    @Valid
    @GetMapping
    public MainVo getMain(){
        return service.getMain();
    }

}

