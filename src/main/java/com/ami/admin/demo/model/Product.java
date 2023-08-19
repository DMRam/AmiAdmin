package com.ami.admin.demo.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </p>
 *
 * @author dannymunoz on 2023-08-18
 * @project demo
 */
@Component
public class Product {
    public String id;
    public String name;
    public double price;

    public Product() {
    }

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    // This method will be automatically called after the bean is created by Spring
    @PostConstruct
    public void init() {
        System.out.println("Product bean has been initialized.");
    }

    // This method will be automatically called before the bean is destroyed by Spring
    @PreDestroy
    public void destroy() {
        System.out.println("Product bean is being destroyed.");
    }
}
