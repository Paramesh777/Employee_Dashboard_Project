package org.employee.employee_dashboard;

import java.util.List;
public interface EmployeeService {
    
    void createEmployee(Employee employee);
    List<Employee> readEmployees();
    boolean deleteEmployees(Integer id);
    boolean updateEmployee(Integer id, Employee employee);
    Employee readEmployee(Integer id);
    
}
