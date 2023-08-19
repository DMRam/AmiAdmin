package com.ami.admin.demo.util;

import com.ami.admin.demo.model.Business;
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
public class BusinessUtil {

    public List<Business> businessListFactory(DataSnapshot dataSnapshot) {
        List<Business> businessList = new ArrayList<>();
        try {
            if (dataSnapshot.exists()) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String id = childSnapshot.getKey();
                    String name = childSnapshot.child("name").getValue(String.class);
                    String address = childSnapshot.child("address").getValue(String.class);
                    Integer employeesCount = childSnapshot.child("employeesCount").getValue(Integer.class);
                    Business business = new Business(id, name, address, employeesCount,9);
                    businessList.add(business);
                }
            }
            return businessList;

        } catch (Exception e) {
            System.out.println("EXCEPTION UTIL: " + e);
        }
        return null;
    }
}
