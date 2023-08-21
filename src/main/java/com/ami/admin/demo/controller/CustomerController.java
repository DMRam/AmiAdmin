package com.ami.admin.demo.controller;

import com.ami.admin.demo.model.Customer;
import com.ami.admin.demo.service.CustomerDataService;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerDataService customerDataService;

    /**
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getBusiness() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(customerDataService.getCustomerData().get(), HttpStatus.OK);
    }

    /**
     * <p>
     * Create a Customer
     * </p>
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) {

        customerDataService.addCustomerData(customer);
    }

    /**
     * @param id
     */
    @DeleteMapping("/delete/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerModel(@RequestParam String id) {

        customerDataService.deleteCustomerById(id);
    }

    /**
     * @param customer
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomerModel(@RequestBody Customer customer) {
        customerDataService.updateCustomerById(customer);
    }
}
