package com.ami.admin.demo.controller;

import com.ami.admin.demo.model.Sale;
import com.ami.admin.demo.service.SaleDataService;
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
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    SaleDataService saleDataService;

    /**
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/all")
    public ResponseEntity<List<Sale>> getSale() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(saleDataService.getSaleData().get(), HttpStatus.OK);
    }

    /**
     *
     * @param sale
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSale(@RequestBody Sale sale) {

        saleDataService.addSaleData(sale);
    }

    /**
     * @param id
     */
    @DeleteMapping("/delete/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSaleModel(@RequestParam String id) {

        saleDataService.deleteSaleById(id);
    }

    /**
     *
     * @param sale
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateSaleModel(@RequestBody Sale sale) {
        saleDataService.updateSaleById(sale);
    }
}
