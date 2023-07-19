package com.woven.gavin.fs.api.controller;


import com.woven.gavin.domain.service.FileStoreService;
import com.woven.gavin.fs.api.dto.FileDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
                      .map(f -> f.getName())
                      .collect(Collectors.toList());
    }
}