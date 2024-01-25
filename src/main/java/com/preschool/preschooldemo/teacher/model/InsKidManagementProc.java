package com.preschool.preschooldemo.teacher.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class InsKidManagementProc {
    private List<Integer> ikids;
    private int iclass;
}
