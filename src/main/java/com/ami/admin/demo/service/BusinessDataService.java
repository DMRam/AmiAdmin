package com.ami.admin.demo.service;

import com.ami.admin.demo.model.Business;
import com.ami.admin.demo.util.BusinessUtil;
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
public class BusinessDataService {

    @Autowired
    private FirebaseApp firebaseApp;
    @Autowired
    private FirebaseDatabase firebaseDatabase;
    @Autowired
    BusinessUtil businessUtil;


    /**
     * <p>
     *
     * </p>
     *
     * @return
     */
    public CompletableFuture<List<Business>> getData() {

        CompletableFuture<List<Business>> future = new CompletableFuture<>();

        try {
            DatabaseReference databaseReference = firebaseDatabase.getReference("ami_admin_bus");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // BusinessUtil creates List of Business from DataSnapshot
                    future.complete(businessUtil.businessListFactory(dataSnapshot));
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
     * <p>
     * This method is adding Business models to Firebase
     * </p>
     *
     * @param business
     */
    public void addData(@RequestBody Business business) {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ami_admin_bus").push();
            String key = ref.getKey();
            business.setId(key);

            ref.setValue(business, new DatabaseReference.CompletionListener() {
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
}
