package com.preschool.preschooldemo.kid;

import com.preschool.preschooldemo.kid.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class KidMapperTest {

    @Autowired
    private KidMapper kidMapper;

    @Test
    void kidProfile() {
        int ikid = 4;

        KidProfileVo vo = kidMapper.kidProfile(ikid);

        assertEquals("문준휘", vo.getKidNm());
    }

    @Test
    void kidGrowth() {
        int ikid = 1;
        int year = 2024;
        List<KidGrowth> list = kidMapper.kidGrowth(ikid, year);
        assertEquals(1, list.size());
    }

    @Test
    void kidParent() {
        int ikid = 1;
        List<KidParent> list = kidMapper.kidParent(ikid);
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("원아 등록")
    void kidSignup() {
        KidInsDto dto = new KidInsDto();
        dto.setKidNm("이동글");
        dto.setBirth("2020-06-06");
        dto.setGender(1);
        dto.setIclass(2);
        dto.setAddress("서울 동글구");
        dto.setProfile("동글동글.jpg");
        dto.setCode("o0606");
        dto.setMemo("귀여워");
        dto.setEmerNm("이둥글");
        dto.setEmerNb("010-0606-0606");

        int affectedRows = kidMapper.kidSignup(dto);
        assertEquals(1, affectedRows, "1");

        int selAffectedRows = kidMapper.kidUpdPic(dto);
        assertEquals(1, selAffectedRows, "2"); // auto_increment

    }


    @Test
    void kidGrowthInsDetail() {
        KidDetailInsDto dto = new KidDetailInsDto();
        dto.setIkid(1);
        dto.setGrowthQuarterly(2);
        dto.setGrowthDate("2024-06-06");

        int result = kidMapper.kidGrowthInsDetail(dto);
        assertEquals(1, result);

    }

    @Test
    void kidBodyInsDetail() {
        KidDetailInsDto dto = new KidDetailInsDto();
        dto.setIkid(1);
        dto.setGrowthQuarterly(2);
        dto.setGrowth(2);
        dto.setGrowthDate("2024-05-22");
        dto.setGrowthMemo("good");

        int result = kidMapper.kidGrowthInsDetail(dto);
        assertEquals(1, result);
    }

    @Test
    void kidGrowthUpdDetail() {
        KidDetailUpdDto dto = new KidDetailUpdDto();
        dto.setIkid(1);
        dto.setGrowth(2);
        dto.setGrowthQuarterly(2);
        dto.setGrowthDate("2024-01-22");
        dto.setGrowthMemo("good");

        int result = kidMapper.kidGrowthUpdDetail(dto);
        assertEquals(1, result);
    }

    @Test
    void kidBodyUpdDetail() {
        KidDetailUpdDto dto = new KidDetailUpdDto();
        dto.setIkid(1);
        dto.setHeight(70);
        dto.setWeight(40);
        dto.setBodyQuarterly(2);
        dto.setBodyDate("2024-01-22");

        int result = kidMapper.kidBodyUpdDetail(dto);
        assertEquals(1, result);
    }

    @Test
    void kidDetailEdit() {
        int ikid = 1;
        KidDetailEditVo vo = kidMapper.kidDetailEdit(ikid);
        assertEquals("최승철", vo.getKidNm());
    }

    @Test
    void kidUpdProfile() {
        int ikid = 1;

        KidDetailEditVo vo = kidMapper.kidDetailEdit(ikid);
        assertEquals("최승철", vo.getKidNm());
    }

    @Test
    void kidEdit() {
        KidUpdDto dto = new KidUpdDto();
        dto.setKidNm("이강쥐");
        dto.setBirth("2020-04-12");
        dto.setGender(1);
        dto.setIclass(1);
        dto.setAddress("서울 멍멍구");
        dto.setProfile("근엄강쥐");
        dto.setEmerNb("010-0412-0412");
        dto.setEmerNm("이강지");
        dto.setMemo("이러쿵");
        dto.setIkid(1);

        int result = kidMapper.kidUpdProfile(dto);
        assertEquals(1, result);
    }
}

