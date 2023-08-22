package com.ami.admin.demo.service;

import com.ami.admin.demo.model.Employee;
import com.ami.admin.demo.util.EmployeeUtil;
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
public class EmployeeDataService {



    @Autowired
    private FirebaseApp firebaseApp;
    @Autowired
    private FirebaseDatabase firebaseDatabase;
    @Autowired
    EmployeeUtil employeeUtil;


    /**
     * <p>
     *
     * </p>
     *
     * @return
     */
    public CompletableFuture<List<Employee>> getEmployeeData() {

        CompletableFuture<List<Employee>> future = new CompletableFuture<>();

        try {
            DatabaseReference databaseReference = firebaseDatabase.getReference("ami_admin_emp");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // BusinessUtil creates List of Business from DataSnapshot
                    future.complete(employeeUtil.employeeListFactory(dataSnapshot));
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
     *
     * @param employee
     */
    public void addEmployeeData(@RequestBody Employee employee) {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ami_admin_emp").push();
            String key = ref.getKey();
            employee.setId(key);

            ref.setValue(employee, new DatabaseReference.CompletionListener() {
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
    public void deleteEmployeeById(String id) {

        try {
            DatabaseReference ref = firebaseDatabase.getReference("ami_admin_emp/" + id);

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
     *
     * @param employee
     */
    public void updateEmployeeById(Employee employee) {
        try {
            DatabaseReference ref = firebaseDatabase.getReference("ami_admin_emp/" + employee.getId());

            ref.setValue(employee, new DatabaseReference.CompletionListener() {
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
