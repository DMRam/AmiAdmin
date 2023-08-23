package com.ami.admin.demo.service;

import com.ami.admin.demo.model.Product;
import com.ami.admin.demo.util.ProductUtil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * </p>
 *
 * @author dannymunoz on 2023-08-18
 * @project demo
 */
@Service
public class ProductDataService {


    @Autowired
    private FirebaseApp firebaseApp;
    @Autowired
    private FirebaseDatabase firebaseDatabase;
    @Autowired
    ProductUtil productUtil;


    /**
     * @return
     */
    public CompletableFuture<List<Product>> getProductData() {

        CompletableFuture<List<Product>> future = new CompletableFuture<>();

        try {
            DatabaseReference databaseReference = firebaseDatabase.getReference("ami_admin_pro");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // BusinessUtil creates List of Business from DataSnapshot
                    future.complete(productUtil.productListFactory(dataSnapshot));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                    future.completeExceptionally(databaseError.toException());
                }
            });
        } catch (Exception e) {
            // Handle the exception here
            future.completeExceptionally(e);
        }

        return future;
    }

    /**
     * @param product
     */
    public void addProductData(@RequestBody Product product) {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ami_admin_pro").push();
            String key = ref.getKey();
            product.setId(key);

            ref.setValue(product, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if (error != null) {
                        // Handle the error
                        System.out.println("Error while setting value: " + error.getMessage());
                    } else {
                        // The operation completed successfully
                        System.out.println("The operation completed successfully");
                    }
                }
            });
        } catch (Exception e) {
            // Handle the exception here
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }

    /**
     * @param id
     */
    public void deleteProductById(String id) {

        try {
            DatabaseReference ref = firebaseDatabase.getReference("ami_admin_pro/" + id);

            System.out.println("Reference to be removed: " + ref);

            ref.removeValue((error, ref1) -> {
                if (error != null) {
                    System.err.println("Error removing element: " + error.getMessage());
                } else {
                    System.out.println("Element removed successfully");
                }
            });
        } catch (Exception e) {
            // Handle the exception here
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }

    /**
     * @param product
     */
    public void updateProductById(Product product) {
        try {
            DatabaseReference ref = firebaseDatabase.getReference("ami_admin_pro/" + product.getId());

            ref.setValue(product, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if (error != null) {
                        // Handle the error
                        System.out.println("Not Done");
                    } else {
                        // The operation completed successfully
                        System.out.println("The operation completed successfully");
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }

    }

}
