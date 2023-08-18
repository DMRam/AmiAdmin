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
public class Business {

    private String name;
    private String address;
    private int employeesCount;
    private double revenue;

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

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
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
