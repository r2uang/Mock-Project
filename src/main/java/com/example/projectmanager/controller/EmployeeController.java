package com.example.projectmanager.controller;

import com.example.projectmanager.entities.*;
import com.example.projectmanager.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeStatusService employeeStatusService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private LanguageService languageService;
    @Autowired
    private EmployeeProjectService employeeProjectService;

//    @RequestMapping("")
//    public String listEmployee(ModelMap model) {
//        String keyword = "";
//        return findPaginated(1, "", -1L, -1L, model);
//    }

//    @RequestMapping("page/{pageNo}")
//    public String findPaginated(@PathVariable(name = "pageNo") int pageNo, @Param("keyword") String keyword, @Param("role") Long role, @Param("language") Long language_id, ModelMap model) {
//        String title = "Employee";
//        int pageSize = 10;
//        Page<Employee> employeeList = employeeService.findPaginatedWithKeyword(pageNo, pageSize, keyword);
////        Page<Employee> employeeList = employeeService.findPaginatedWithRoleId(pageNo, pageSize, role);
////        Page<Employee> employeeList = employeeService.findAllByEmployeeNameAndRoleAndLanguages(pageNo, pageSize, role ,language_id,keyword);
//        List<Role> roleList = roleService.listAll();
//        List<Language> languageList = languageService.listAll();
//        model.addAttribute("employeeList", employeeList);
//        model.addAttribute("roleList", roleList);
//        model.addAttribute("title", title);
//        model.addAttribute("employee", new Employee());
//        model.addAttribute("languageList", languageList);
//        model.addAttribute("keyword", keyword);
//        return "employee";
//    }
    @GetMapping("/list")
    public String getAllEmployee(ModelMap model,@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                 @ModelAttribute(name = "filter") FilterRequest filterRequest){
        String title = "Employee List";
        if(filterRequest.getPageSize() == 0){
            filterRequest.setPageSize(10);
        }
        if (filterRequest.getKeyword() == null) {
            filterRequest.setKeyword("");
        }
        if (filterRequest.getStatus() == 0 ) {
            filterRequest.setStatus(0);
        }
        if (filterRequest.getLanguage() == 0) {
            filterRequest.setLanguage(0);
        }
        if (filterRequest.getRole() == 0) {
            filterRequest.setRole(0);
        }
        Page<Employee> paginated = employeeService.getEmployeePaginated(pageNumber,filterRequest);
        if(pageNumber < 0 || pageNumber > paginated.getTotalPages()){
            pageNumber = 1;
            return "redirect:list";
        }
        List<Employee> allEmployee = paginated.getContent();
        if(allEmployee.size() == 0 || allEmployee == null){
            model.addAttribute("allEmployee",null);
        }
        else {
            model.addAttribute("allEmployee",allEmployee);
        }
        model.addAttribute("filter",new FilterRequest());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", paginated.getTotalPages());
        model.addAttribute("totalItems", paginated.getTotalElements());
        model.addAttribute("employee",new Employee());
        model.addAttribute("filterRequest",filterRequest);
        model.addAttribute("keyword",filterRequest.getKeyword());
        model.addAttribute("roles",roleService.listAll());
        model.addAttribute("languages",languageService.listAll());
        model.addAttribute("title",title);
        return "employee";
    }
    @RequestMapping(value = "/add")
    public String addEmployee(ModelMap model, Employee employee) {
        String title = "Add New Employee";
        List<EmployeeStatus> employeeStatuses = employeeStatusService.listAll();
        model.addAttribute("employeeStatuses", employeeStatuses);
        List<Role> roleList = roleService.listAll();
        model.addAttribute("roleList", roleList);
        List<Language> languageList = languageService.listAll();
        model.addAttribute("languageList", languageList);
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", title);
        return "addEmployee";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detailEmployee(ModelMap model, @PathVariable("id") Long id) {
        String title = "Employee Detail";
        model.addAttribute("title", title);
        List<Role> roleList = roleService.listAll();
        model.addAttribute("roleList", roleList);
        List<EmployeeStatus> employeeStatuses = employeeStatusService.listAll();
        model.addAttribute("employeeStatuses", employeeStatuses);
        List<Language> languageList = languageService.listAll();
        model.addAttribute("languageList", languageList);
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        List<EmployeeProject> employeeProjectList = employeeProjectService.getAllProjectByEmployeeId(id);
        model.addAttribute("employeeProjectList", employeeProjectList);
        return "updateEmployee";
    }

    @PostMapping(value = "/update")
    public String updateEmployee(ModelMap model, Employee employee) {
        employee.setUpdateAt(Date.valueOf(LocalDate.now()));
        employeeService.saveOrUpdateEmployee(employee);
        return "redirect:list";
    }

    @PostMapping(value = "/save")
    public String saveEmployee(ModelMap model, Employee employee) {
        employee.setDateJoin(Date.valueOf(LocalDate.now()));
        employee.setUpdateAt(Date.valueOf(LocalDate.now()));
        employeeService.saveOrUpdateEmployee(employee);
        return "redirect:list";
    }
}
