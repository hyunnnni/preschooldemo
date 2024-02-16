package com.preschool.preschooldemo.kid;

import com.preschool.preschooldemo.common.security.AuthenticationFacade;
import com.preschool.preschooldemo.common.utils.MyFileUtils;
import com.preschool.preschooldemo.kid.model.KidParent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({KidService.class})
class KidServiceTest {

    @MockBean
    private KidMapper mapper;
    @MockBean
    private MyFileUtils myFileUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private KidService service;


    @Test
    void kidProfile() {
//        KidProfileVo test = new KidProfileVo();
//        test.setKidNm("곽경록");
//        test.setResult(1);
//
//        when(mapper.kidProfile(anyInt())).thenReturn(test);


        KidParent parent = new KidParent();
        parent.setUid("sss");

        KidParent parent1 = new KidParent();
        parent1.setUid("zzz");

        List<KidParent> result = new ArrayList();
        result.add(parent);
        result.add(parent1);

        when(mapper.kidParent((1))).thenReturn(result);

//        KidGrowth kidGrowth = new KidGrowth();
//        kidGrowth.setHeight(116);
//        kidGrowth.setWeight(32);
//
//        KidGrowth kidGrowth1 = new KidGrowth();
//        kidGrowth1.setWeight(31);
//        kidGrowth1.setHeight(134);
//
//        List<KidGrowth> result2 = new ArrayList();
//        result2.add(kidGrowth);
//        result2.add(kidGrowth1);
//
//        //when(mapper.kidGrowth(any(),any())).thenReturn(result2);

        service.kidProfile(1,1);


        
        
        



    }

    @Test
    void kidCode() {
    }

    @Test
    void kidSignup() {
    }

    @Test
    void kidInsDetail() {
    }

    @Test
    void kidUpdDetail() {
    }

    @Test
    void kidDetailEdit() {
    }

    @Test
    void kidUpdProfile() {
    }

    @Test
    void kidEdit() {
    }

    @Test
    void allGraduateKid() {
    }
}