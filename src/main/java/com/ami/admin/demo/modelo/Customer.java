package com.ami.admin.demo.modelo;

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
public class Customer {

    public String id;
    public String name;
    public String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    // This method will be automatically called after the bean is created by Spring
    @PostConstruct
    public void init() {
        System.out.println("Business bean has been initialized.");
    }

    // This method will be automatically called before the bean is destroyed by Spring
    @PreDestroy
    public void destroy() {
        System.out.println("Business bean is being destroyed.");
    }
}
