package com.preschool.preschooldemo.main.model;

import lombok.Data;

import java.util.List;

@Data
public class MainVo {
    private List<AlbumMainVo> albumMainVoList;
    private List<SelFullNoticeVo> fullNoticeVoList;
}
