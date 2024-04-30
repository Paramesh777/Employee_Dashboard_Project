
package org.employee.employee_dashboard;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/form")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    
    @GetMapping("/readAll")
    public List<Employee> getAllEmployees() {
        return employeeService.readEmployees();
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam("id") Integer id) {
        Employee employee = employeeService.readEmployee(id);
        return ResponseEntity.ok(employee);  
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/createEmployeeAction.html").build();
    }
    
    @PostMapping("/delete") 
    public ResponseEntity<Void> deleteEmployee(@RequestParam("id") Integer id) {
        if(employeeService.deleteEmployees(id))
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/deleteEmployeeByIdAction1.html").build();
        else
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/deleteEmployeeByIdAction2.html").build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateEmployee(@RequestParam("id") Integer id, @ModelAttribute Employee employee) {
        boolean result = employeeService.updateEmployee(id, employee);
        ResponseEntity.ok(result);
        if(result)
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/updateEmployeeAction1.html").build();
        else
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/updateEmployeeAction2.html").build();
            
    }
}