package com.ami.admin.demo.service;

import com.ami.admin.demo.model.Customer;
import com.ami.admin.demo.model.Sale;
import com.ami.admin.demo.util.CustomerUtil;
import com.ami.admin.demo.util.SaleUtil;
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
public class SaleDataService {


    @Autowired
    private FirebaseApp firebaseApp;
    @Autowired
    private FirebaseDatabase firebaseDatabase;
    @Autowired
    SaleUtil saleUtil;


    /**
     * <p>
     *
     * </p>
     *
     * @return
     */
    public CompletableFuture<List<Sale>> getSaleData() {

        CompletableFuture<List<Sale>> future = new CompletableFuture<>();

        try {
            DatabaseReference databaseReference = firebaseDatabase.getReference("ami_admin_sale");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // BusinessUtil creates List of Business from DataSnapshot
                    future.complete(saleUtil.saleListFactory(dataSnapshot));
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
     * @param sale
     */
    public void addSaleData(@RequestBody Sale sale) {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ami_admin_sale").push();
            String key = ref.getKey();
            sale.setId(key);

            ref.setValue(sale, new DatabaseReference.CompletionListener() {
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
    public void deleteSaleById(String id) {

        try {
            DatabaseReference ref = firebaseDatabase.getReference("ami_admin_sale/" + id);

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
     * @param sale
     */
    public void updateSaleById(Sale sale) {
        try {
            DatabaseReference ref = firebaseDatabase.getReference("ami_admin_sale/" + sale.getId());

            ref.setValue(sale, new DatabaseReference.CompletionListener() {
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
