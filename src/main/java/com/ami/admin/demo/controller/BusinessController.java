package com.ami.admin.demo.controller;

import com.ami.admin.demo.model.Business;
import com.ami.admin.demo.service.BusinessDataService;
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
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    BusinessDataService businessDataService;

    @GetMapping("/all")
    public ResponseEntity<List<Business>> getBusiness() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(businessDataService.getData().get(), HttpStatus.OK);
    }

    /**
     * <p>
     * Create an Business
     * </p>
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDataToFirebase(@RequestBody Business business) {

        businessDataService.addData(business);
    }
}
