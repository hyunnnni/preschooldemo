package com.preschool.preschooldemo.memory.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public class InsRoomInviteProcDto {
    private int imemory;
    private List<Integer> iparents;
}
