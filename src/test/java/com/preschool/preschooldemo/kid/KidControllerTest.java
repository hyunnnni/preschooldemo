package com.preschool.preschooldemo.kid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.preschool.preschooldemo.MockMvcConfig;
import com.preschool.preschooldemo.kid.model.KidProfileVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@MockMvcConfig
@Import(KidController.class)
//@WebMvcTest({KidController.class})
class KidControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private KidService service;
    @Autowired
    private ObjectMapper mapper;

    String token;

   /* @BeforeEach
    public void before() throws Exception {
        TeacherSigninDto dto = new TeacherSigninDto();
        dto.setTeacherUid("123");
        dto.setTeacherUpw("123");

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();
        TeacherEntity signinVo = mapper.readValue(contentAsString, TeacherEntity.class);
        this.token = "Bearer " + signinVo.getAccessToken();
    }
*/
    @Test
    void getKidProfile() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); //스프링 꺼 해쉬맵과 비슷?
        params.add("year","2024");
        params.add("ikid","1");

        KidProfileVo result = new KidProfileVo();
        result.setKidNm("최승철");
        result.setIclass(1);
        result.setGender(1);

        given(service.kidProfile(anyInt(), anyInt())).willReturn(result);

        mvc.perform(
                        MockMvcRequestBuilders
                        .get("/api/kid/2024/1")
                )//검증하는부분
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).kidProfile(anyInt(),anyInt());
    }

    @Test
    void patchKidCode() {
    }

    @Test
    void postKidSignup() {
    }

    @Test
    void postKidInsDetail() {
    }

    @Test
    void putKidUpdDetail() {
    }

    @Test
    void getKidDetailEdit() {
    }

    @Test
    void putKidProfile() {
    }

    @Test
    void getKidEdit() {
    }

    @Test
    void delAllGraduateKid() {
    }
}