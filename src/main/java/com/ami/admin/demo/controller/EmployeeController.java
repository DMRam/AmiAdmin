package com.ami.admin.demo.controller;

import com.ami.admin.demo.model.Employee;
import com.ami.admin.demo.service.EmployeeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * </p>
 *
 * @author dannymunoz on 2023-08-18
 * @project demo
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    EmployeeDataService employeeDataService;

    /**
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployee() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(employeeDataService.getEmployeeData().get(), HttpStatus.OK);
    }

    /**
     * <p>
     * Create a Employee
     * </p>
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Employee employee) {

        employeeDataService.addEmployeeData(employee);
    }

    /**
     * @param id
     */
    @DeleteMapping("/delete/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerModel(@RequestParam String id) {

        employeeDataService.deleteEmployeeById(id);
    }

    /**
     * @param employee
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomerModel(@RequestBody Employee employee) {
        employeeDataService.updateEmployeeById(employee);
    }
}
