package com.preschool.preschooldemo.kid.model;

import lombok.Data;

@Data
public class GrowthCheck {
    private int ikid;
    private String growthDate;
    private String bodyDate;
    private int bodyQuarterly;
    private int growthQuarterly;
}
