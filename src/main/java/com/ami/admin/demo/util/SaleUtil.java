package com.ami.admin.demo.util;

import com.ami.admin.demo.model.Business;
import com.ami.admin.demo.model.Customer;
import com.ami.admin.demo.model.Product;
import com.ami.admin.demo.model.Sale;
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
public class SaleUtil {

    public List<Sale> saleListFactory(DataSnapshot dataSnapshot) {
        List<Sale> saleList = new ArrayList<>();
        try {
            if (dataSnapshot.exists()) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String id = childSnapshot.getKey();
                    Customer customer = childSnapshot.child("customer").getValue(Customer.class);
                    Product product = childSnapshot.child("product").getValue(Product.class);
                    Double quantity = childSnapshot.child("quantity").getValue(Double.class);
                    Double total = childSnapshot.child("total").getValue(Double.class);
                    Sale business = new Sale(id, customer, product, quantity, total);
                    saleList.add(business);
                }
            }
            return saleList;

        } catch (Exception e) {
            System.out.println("EXCEPTION UTIL SALE: " + e);
        }
        return null;
    }
}
