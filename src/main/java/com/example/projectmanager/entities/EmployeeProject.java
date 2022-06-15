package com.example.projectmanager.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employee_project")
public class EmployeeProject {
    @EmbeddedId
    EmployeeProjectKey id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "out_date")
    private Date outDate;

    @Column(name = "status")
    private boolean status;

    public EmployeeProjectKey getId() {
        return id;
    }

    public void setId(EmployeeProjectKey id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
