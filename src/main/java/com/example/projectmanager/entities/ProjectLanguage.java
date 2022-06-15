package com.example.projectmanager.entities;

import javax.persistence.*;

@Entity
@Table(name = "project_language")
public class ProjectLanguage {
    @EmbeddedId
    ProjectLanguageKey id;
    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne
    @MapsId("languageId")
    @JoinColumn(name = "language_id")
    private Language language;


    public ProjectLanguage() {
    }

    public ProjectLanguage(Project project, Language language) {
        this.project = project;
        this.language = language;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
