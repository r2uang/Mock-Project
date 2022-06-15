package com.example.projectmanager.entities;

import javax.persistence.*;

@Entity
@Table(name = "employee_language")
public class EmployeeLanguage {
    @EmbeddedId
    EmployeeLanguageKey id;

    @ManyToOne
    @MapsId("employee_id")
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne
    @MapsId("language_id")
    @JoinColumn(name = "language_id")
    Language language;

    public EmployeeLanguage() {
    }

    public EmployeeLanguage(EmployeeLanguageKey id, Employee employee, Language language) {
        this.id = id;
        this.employee = employee;
        this.language = language;
    }

    public EmployeeLanguageKey getId() {
        return id;
    }

    public void setId(EmployeeLanguageKey id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
