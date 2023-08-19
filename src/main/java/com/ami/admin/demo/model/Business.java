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
//@Component
public class Business {

    private String id;
    private String name;
    private String address;
    private long employeesCount;
    private double revenue;

    public Business(String id, String name, String address, long employeesCount, double revenue) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.employeesCount = employeesCount;
        this.revenue = revenue;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "Business{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", employeesCount=" + employeesCount +
                ", revenue=" + revenue +
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
