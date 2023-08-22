package com.ami.admin.demo.util;

import com.ami.admin.demo.model.Employee;
import com.google.firebase.database.DataSnapshot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author dannymunoz on 2023-08-18
 * @project demo
 */
@Component
public class EmployeeUtil {

    public List<Employee> employeeListFactory(DataSnapshot dataSnapshot) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            if (dataSnapshot.exists()) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String id = childSnapshot.getKey();
                    String name = childSnapshot.child("name").getValue(String.class);
                    String lastName = childSnapshot.child("lastName").getValue(String.class);
                    Employee employee = new Employee(id, name, lastName);
                    employeeList.add(employee);
                }
            }
            return employeeList;

        } catch (Exception e) {
            System.out.println("EXCEPTION UTIL EMPLOYEE: " + e);
        }
        return null;
    }
}
