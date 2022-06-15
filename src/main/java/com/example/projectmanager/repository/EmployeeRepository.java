package com.example.projectmanager.repository;

import com.example.projectmanager.entities.Employee;
import com.example.projectmanager.entities.EmployeeProject;
import com.example.projectmanager.entities.FilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.employeeName like %?1% order by e.Id DESC")
    Page<Employee> findAllByEmployeeName(String name, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.role.roleId = ?1 order by e.Id DESC")
    Page<Employee> findAllByRole(Long role_id, Pageable pageable);

    @Query(value = "SELECT e.* FROM project_manager.employee e join project_manager.employee_language el on el.employee_id = e.id WHERE e.employee_name like %/1% \n" + "AND (COALESCE(?2, NULL) IS NULL OR e.role_id = ?2) and (COALESCE(?3, NULL) IS NULL OR el.language_id = ?3)  order by e.id DESC", nativeQuery = true)
    Page<Employee> findAllByEmployeeNameAndRoleAndLanguages(String name, Long role_id, Long language_id, Pageable pageable);

    @Query(value = "select e from Employee e join EmployeeLanguage el on e.Id = el.employee.Id left join ProjectLanguage p on p.language.languageId = el.language.languageId where e.employeeStatus = true and p.project.Id = ?1 ")
    List<Employee> listAllByStatusAndLanguage(Long project_id);

    @Query(value = "select e from Employee e WHERE e.employeeStatus.id = 1")
    List<Employee> getEmployeeByEmployeeStatus();

    @Query(value = "select *,count(ep.employee_id) as total from project_manager.employee_project ep right join employee e on e.id = ep.employee_id where e.employee_status_id = 1 " +
            "and (ep.status = true OR ep.status IS Null) group by e.id having count(ep.employee_id and ep.status = false) < ?1", nativeQuery = true)
    List<Employee> getListEmployeeAvailable(int id);

    @Query(value = "SELECT distinct employee FROM Employee employee left join employee.languages el\n" +
            " where (coalesce(:#{#filter.keyword}, '') = '' or employee.employeeName LIKE CONCAT('%',:#{#filter.keyword},'%'))\n " +
            " and (coalesce(:#{#filter.status}, 0) = 0 or employee.employeeStatus.id = :#{#filter.status})\n " +
            " and (coalesce(:#{#filter.role}, 0) = 0 or employee.role.roleId= :#{#filter.role})\n " +
            " and (coalesce(:#{#filter.language}, 0) = 0 or  el.languageId = :#{#filter.language})\n")
    Page<Employee>filterEmployee(@Param("filter") FilterRequest filterRequest, Pageable pageable);
}
