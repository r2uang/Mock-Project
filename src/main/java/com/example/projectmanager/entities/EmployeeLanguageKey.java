package com.example.projectmanager.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class EmployeeLanguageKey implements Serializable {

    @Column(name = "employee_id")
    private Long employee_id;


    @Column(name = "language_id")
    private Long language_id;

    public EmployeeLanguageKey() {
    }

    public EmployeeLanguageKey(Long employee_id, Long language_id) {
        this.employee_id = employee_id;
        this.language_id = language_id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Long language_id) {
        this.language_id = language_id;
    }
}
