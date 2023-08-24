package com.ami.admin.demo.controller;

import com.ami.admin.demo.model.Product;
import com.ami.admin.demo.service.ProductDataService;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDataService productDataService;

    /**
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProduct() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(productDataService.getProductData().get(), HttpStatus.OK);
    }

    /**
     * @param product
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {

        productDataService.addProductData(product);
    }

    /**
     * @param id
     */
    @DeleteMapping("/delete/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductModel(@RequestParam String id) {

        productDataService.deleteProductById(id);
    }

    /**
     * @param product
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateProductModel(@RequestBody Product product) {
        productDataService.updateProductById(product);
    }
}
