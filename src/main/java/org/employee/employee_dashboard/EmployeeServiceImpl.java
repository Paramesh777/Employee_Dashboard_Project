package org.employee.employee_dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) { 
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
    }

    @Override
    public Employee readEmployee(Integer id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        Employee employee= new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        @SuppressWarnings({ "rawtypes", "unchecked" })
        List<Employee> employees = new ArrayList();
        for(EmployeeEntity employeeEntity: employeesList) {
            Employee emp = new Employee();
            emp.setId(employeeEntity.getId());
            emp.setName(employeeEntity.getName());
            emp.setEmail(employeeEntity.getEmail());
            emp.setPhone(employeeEntity.getPhone());
            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployees(Integer id) {
        try {
            EmployeeEntity emp = employeeRepository.findById(id).get();
            employeeRepository.delete(emp);
            return true;
        }
        catch(NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Integer id, Employee employee) {
        try {
            EmployeeEntity existingEmployee = employeeRepository.findById(id).get();
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setName(employee.getName());
            existingEmployee.setPhone(employee.getPhone());
            employeeRepository.save(existingEmployee);
            return true;
        }
        catch(NoSuchElementException e) {
            return false;
        }
    }
}
