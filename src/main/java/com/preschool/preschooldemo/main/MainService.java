package com.preschool.preschooldemo.main;

import com.preschool.preschooldemo.main.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {
    private final MainMapper mapper;

    // 메인화면 조회
    public MainVo getMain() {

        MainDto dto = new MainDto();
        MainVo list = new MainVo();

        List<AlbumMainVo> album = mapper.selMainAlbum(dto);
        list.setAlbumMainVoList(album);

        List<SelFullNoticeVo> noticeFix = mapper.getFullNoticeFix();
        list.setFullNoticeVoList(noticeFix);

        return list;
    }
}