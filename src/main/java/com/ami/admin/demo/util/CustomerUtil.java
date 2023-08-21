package com.ami.admin.demo.util;

import com.ami.admin.demo.model.Business;
import com.ami.admin.demo.model.Customer;
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
public class CustomerUtil {

    public List<Customer> customerListFactory(DataSnapshot dataSnapshot) {
        List<Customer> customerList = new ArrayList<>();
        try {
            if (dataSnapshot.exists()) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String id = childSnapshot.getKey();
                    String name = childSnapshot.child("name").getValue(String.class);
                    String address = childSnapshot.child("address").getValue(String.class);
                    Customer customer = new Customer(id,name,address);
                    customerList.add(customer);
                }
            }
            return customerList;

        } catch (Exception e) {
            System.out.println("EXCEPTION UTIL: " + e);
        }
        return null;
    }
}
