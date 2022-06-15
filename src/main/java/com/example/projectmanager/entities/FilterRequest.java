package com.example.projectmanager.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class FilterRequest {
    private int pageSize;
    private String keyword;
    private long role;
    private long status;
    private long language;
    private Date startDate;
    private Date endDate;
}
