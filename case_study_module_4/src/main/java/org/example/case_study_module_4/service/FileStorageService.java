package org.example.case_study_module_4.service;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.IOException;

@Service
public class FileStorageService {
    public String storeFile(MultipartFile media) {
        String fileUrl = null;
        try (InputStream inputStream = media.getInputStream()) {
            Bucket bucket = StorageClient.getInstance().bucket();
            Blob blob = bucket.create(media.getOriginalFilename(), inputStream, media.getContentType());
            fileUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s%s", blob.getBucket(), blob.getName(), "?alt=media&token=d56d7555-bced-4ac9-9e9d-0389d5ba18a4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }
}

