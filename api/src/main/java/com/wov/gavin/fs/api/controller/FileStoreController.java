package com.wov.gavin.fs.api.controller;


import com.wov.gavin.domain.service.FileStoreService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileStoreController {

    private final FileStoreService service;

    @PostMapping(value = "/files", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "upload file", notes = "Over write is not supported, return 400 if the file exists",
                  response = void.class)
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        //TODO overWrite is set as false by default, not to implement in API in the submitted job.
        service.uploadFile(fileName, file, false);
    }

    @DeleteMapping("/files/{fileName}")
    @ApiOperation(value = "delete file", notes = "return 400 if the file doesn't exist",
                  response = void.class)
    public void deleteFile(@PathVariable(name = "fileName") String fileName) {
        service.deleteFile(fileName);
    }

    @GetMapping("/files")
    @ApiOperation(value = "list files", notes = "list all files, directory is excluded")
    public List<String> listFile() {
        return service.listFile()
                      .stream()
                      .map(File::getName)
                      .collect(Collectors.toList());
    }
}