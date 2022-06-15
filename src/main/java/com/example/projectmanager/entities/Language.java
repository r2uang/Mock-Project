package com.example.projectmanager.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long languageId;

    @Column(name = "language_name")
    private String languageName;

    @ManyToMany(mappedBy = "languages")
    private Set<Employee> employees = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "languages")
    private Set<Project> projects = new LinkedHashSet<>();
    public Language() {
    }

    public Language(Long languageId, String languageName) {
        this.languageId = languageId;
        this.languageName = languageName;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
