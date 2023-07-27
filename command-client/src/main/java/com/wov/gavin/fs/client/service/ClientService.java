package com.wov.gavin.fs.client.service;

import com.wov.gavin.fs.client.template.ClientTemplate;
import java.io.File;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private static final ParameterizedTypeReference<List<String>> fileListTypeReference = new ParameterizedTypeReference<>() {
    };
    private final String PATH = "/files";

    private final ClientTemplate template;

    public String uploadFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return "File " + fileName + " doesn't exist";
        }
        if (!file.isFile()) {
            return fileName + " is not a file";
        }

        FileSystemResource fileSystemResource = new FileSystemResource(file);

        ResponseEntity<String> responseEntity = template.postFile(PATH, "file", fileSystemResource, String.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return "File " + fileName + " is uploaded";
        }
    }

    public List<String> listFile() {

        ResponseEntity<List<String>> responseEntity = template.get(PATH, fileListTypeReference);
        return responseEntity.getBody();
    }

    public String deleteFile(String fileName) {
        template.delete(PATH + "/" + fileName, String.class);
        return "File " + fileName + " is deleted";
    }
}
