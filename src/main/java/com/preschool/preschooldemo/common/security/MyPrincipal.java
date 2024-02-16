package com.preschool.preschooldemo.common.security;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPrincipal {//로그인 시 사용자의 pk값 외 다른 값을 받을때 유연하게 사용됨
    private int iuser;

    private int ilevel;

    @Builder.Default //빌더 패턴을 쓸때 빌더에서 안적으면 디폴트를 이것으로 쓰겠다. 지금은 뉴 어레이 리스트
    private List<String> roles = new ArrayList<>();


}
