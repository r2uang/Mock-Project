package com.example.projectmanager.controller;

import com.example.projectmanager.entities.*;
import com.example.projectmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toSet;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeProjectService employeeProjectService;

    @Autowired
    private ProjectStatusService projectStatusService;

    @RequestMapping("")
    public String listProject(ModelMap model) {
        String keyword = "";
        return findPaginated(1, keyword, model);
    }

    @RequestMapping("page/{pageNo}")
    private String findPaginated(@PathVariable(name = "pageNo") int pageNo, @Param("keyword") String keyword, ModelMap model) {
        String title = "Project";
        int pageSize = 10;
        Page<Project> projectList = projectService.findPaginatedWithKeyword(pageNo, pageSize, keyword);
        List<Role> roleList = roleService.listAll();
        List<Language> languageList = languageService.listAll();
        model.addAttribute("projectList", projectList);
        model.addAttribute("roleList", roleList);
        model.addAttribute("title", title);
        model.addAttribute("project", new Project());
        model.addAttribute("languageList", languageList);
        model.addAttribute("keyword", keyword);
        return "project";
    }

    @RequestMapping(value = "/add")
    public String addProject(ModelMap model) {
        String title = "Add New Project";
        List<Language> languageList = languageService.listAll();
        model.addAttribute("languageList", languageList);
        model.addAttribute("project", new Project());
        model.addAttribute("title", title);
        return "addProject";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detailProject(ModelMap model, @PathVariable("id") Long id) {
        String title = "Project Detail";
        model.addAttribute("title", title);
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        List<Language> languageList = languageService.listAll();
        model.addAttribute("languageList", languageList);
        List<Employee> employeeList = employeeService.getAvailableEmployeeForProject(project);
        model.addAttribute("employeeList", employeeList);
        if (project.getEmployeeProjects() == null || project.getEmployeeProjects().size() == 0) {
            model.addAttribute("employeeProject", null);
        } else {
            model.addAttribute("employeeProject", employeeProjectService.getAllEmployeeByProjectId(project.getId()));
        }
        model.addAttribute("projectEm", new EmployeeProject());
        return "updateProject";
    }

    @PostMapping(value = "/save")
    public String saveProject(ModelMap model, Project project) {
        ProjectStatus projectStatus = projectStatusService.findById(1L);
        project.setProjectStatus(projectStatus);
        projectService.saveOrUpdateProject(project);
        return "redirect:";
    }

    @PostMapping(value = "/update")
    public String updateProject(ModelMap model, EmployeeProject employeeProject) {
        EmployeeProjectKey employeeProjectKey = new EmployeeProjectKey();
        employeeProjectKey.setProjectId(employeeProject.getProject().getId());
        employeeProjectKey.setEmployeeId(employeeProject.getEmployee().getId());
        employeeProject.setId(employeeProjectKey);
        employeeProject.setJoinDate(Date.valueOf(LocalDate.now()));
        employeeProject.setStatus(true);
        employeeProjectService.saveOrUpdateEmployeeProject(employeeProject);
        return "redirect:/projects/detail/" + employeeProject.getProject().getId();
    }

    @GetMapping(value = "delete/employee/{eid}/{pid}")
    public String deleteEmployeeInProject(Model model, @PathVariable("eid") Long eid, @PathVariable("pid") Long pid) {
        EmployeeProject employeeProject = employeeProjectService.findByEmployeeIdAndProjectId(eid, pid);
        employeeProject.setOutDate(Date.valueOf(LocalDate.now()));
        employeeProject.setStatus(false);
        employeeProjectService.saveOrUpdateEmployeeProject(employeeProject);
        return "redirect:/projects/detail/" + pid;

    }

    @GetMapping(value = "close/{pid}")
    public String closeProject(Model model, @PathVariable("pid") Long pid) {
        Project project = projectService.findById(pid);
        ProjectStatus status = projectStatusService.findById(2L);
        project.setProjectStatus(status);
        Set<EmployeeProject> employeeProjects = project.getEmployeeProjects();
        for (EmployeeProject employeeProject : employeeProjects) {
            employeeProject.setOutDate(Date.valueOf(LocalDate.now()));
            employeeProject.setStatus(false);
            employeeProjectService.saveOrUpdateEmployeeProject(employeeProject);
        }
        projectService.saveOrUpdateProject(project);
        return "redirect:/projects/detail/" + pid;

    }

    @GetMapping(value = "open/{pid}")
    public String openProject(Model model, @PathVariable("pid") Long id) {
        Project project = projectService.findById(id);
        ProjectStatus status = projectStatusService.findById(1L);
        project.setProjectStatus(status);
        Set<EmployeeProject> employeeProjects = project.getEmployeeProjects();
        for (EmployeeProject employeeProject : employeeProjects) {
            employeeProject.setOutDate(Date.valueOf(LocalDate.now()));
            employeeProject.setStatus(true);
            employeeProjectService.saveOrUpdateEmployeeProject(employeeProject);
        }
        projectService.saveOrUpdateProject(project);
        return "redirect:/projects/detail/" + id;

    }
}
