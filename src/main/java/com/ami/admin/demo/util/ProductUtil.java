package com.ami.admin.demo.util;

import com.ami.admin.demo.model.Product;
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
public class ProductUtil {

    public List<Product> productListFactory(DataSnapshot dataSnapshot) {
        List<Product> productList = new ArrayList<>();
        try {
            if (dataSnapshot.exists()) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String id = childSnapshot.getKey();
                    String name = childSnapshot.child("name").getValue(String.class);
                    Double price = childSnapshot.child("price").getValue(Double.class);
                    Product product = new Product(id, name, price);
                    productList.add(product);
                }
            }
            return productList;

        } catch (Exception e) {
            System.out.println("EXCEPTION UTIL PRODUCT: " + e);
        }
        return null;
    }
}
