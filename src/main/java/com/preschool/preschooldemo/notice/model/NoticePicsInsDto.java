package com.preschool.preschooldemo.notice.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class NoticePicsInsDto {
    private int inotice;
    private List<String> pics = new ArrayList<>();
}
