package org.example.case_study_module_4.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount = getClass().getClassLoader()
                    .getResourceAsStream("casestudy-c10eb-firebase-adminsdk-uv8z7-abbe1e0d8e.json");
            if (serviceAccount == null) {
                throw new FileNotFoundException("Service account key not found in the classpath");
            }
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://casestudy-c10eb-default-rtdb.firebaseio.com")
                    .setStorageBucket("casestudy-c10eb.appspot.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}