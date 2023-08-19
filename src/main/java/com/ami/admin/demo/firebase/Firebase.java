package com.ami.admin.demo.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class Firebase {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(
                "/Users/dannymunoz/Documents/Development/" +
                        "Portfolio/AmiAdmin/Spring/ami-admin-backend/" +
                        "amiadmin-firebase-adminsdk-l6ylt-dd1e1dbc0f.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://amiadmin-default-rtdb.firebaseio.com/")
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseDatabase firebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }


}
