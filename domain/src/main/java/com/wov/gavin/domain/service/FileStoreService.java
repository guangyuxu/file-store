package com.wov.gavin.domain.service;

import com.wov.gavin.domain.exception.FileExistException;
import com.wov.gavin.domain.exception.FileNotExistException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStoreService {

    private final String fileUploadPath;

    public void uploadFile(String fileName, InputStreamSource inputStreamSource, boolean overWrite) {
        log.info("Upload file name:{}, type:{}, over write:{}", fileName, inputStreamSource.getClass(), overWrite);
        File targetFile = getFileByName(fileName);
        beforeUploadFile(fileName, overWrite);
        try (InputStream inputStream = inputStreamSource.getInputStream()) {
            java.nio.file.Files.copy(
                    inputStream,
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("failed on upload file " + fileName, e);
        }
        log.info("Upload file name:{}, type:{}, over write:{} successfully", fileName, inputStreamSource.getClass(), overWrite);
    }

    public void beforeUploadFile(String fileName, boolean overWrite) {
        File file = getFileByName(fileName);
        if (file.exists()) {
            if (overWrite) {
                file.delete();
            } else {
                throw new FileExistException(file.getName());
            }
        }
    }

    public void deleteFile(String fileName) {
        log.info("Delete file name:{}", fileName);
        File file = getFileByName(fileName);
        if (!file.exists()) {
            throw new FileNotExistException(fileName);
        }
        file.delete();
        log.info("Delete file name:{} successfully", fileName);
    }

    public List<File> listFile() {
        File filePath = new File(fileUploadPath);
        return Arrays.stream(Objects.requireNonNull(filePath.listFiles())).collect(Collectors.toList());
    }

    File getFileByName(String fileName) {
        return new File(fileUploadPath + File.separator + fileName);
    }
}
