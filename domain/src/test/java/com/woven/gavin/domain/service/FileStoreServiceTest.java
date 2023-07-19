package com.woven.gavin.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.woven.gavin.domain.exception.FileExistException;
import com.woven.gavin.domain.exception.FileNotExistException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.Assert;


public class FileStoreServiceTest {

    private static String fileUploadPath = System.getProperty("user.home") + "/woven/gavin/file-store/test";
    private static FileStoreService service;

    @Test
    public void test_upload_a_new_file() throws IOException {
        String fileName = "test_upload_a_new_file.txt";
        File file = new File(fileUploadPath + "/" + fileName);

        ByteArrayResource resource = new ByteArrayResource("test: test_upload_a_new_file".getBytes());
        service.uploadFile(fileName, resource, false);

        Assert.isTrue(file.exists(), "The new file " + fileName + " is uploaded successfully");
        Assert.isTrue("test: test_upload_a_new_file".equals(getFileContent(file)), "Text in file is same as input");
    }

    @Test
    public void test_upload_a_new_file_with_over_write() throws IOException {
        String fileName = "test_upload_a_new_file_with_over_write.txt";
        File file = new File(fileUploadPath + "/" + fileName);

        ByteArrayResource resource = new ByteArrayResource("test: test_upload_a_new_file_with_over_write".getBytes());
        service.uploadFile(fileName, resource, true);

        Assert.isTrue(file.exists(), "The new file " + fileName + " is uploaded successfully");
        Assert.isTrue("test: test_upload_a_new_file_with_over_write".equals(getFileContent(file)), "Text in file is same as input");
    }

    @Test
    public void test_upload_with_duplicated_filename_non_overwrite() throws IOException {
        String fileName = "test_upload_with_duplicated_filename_non_overwrite.txt";
        File file = new File(fileUploadPath + "/" + fileName);

        service.uploadFile(fileName, new ByteArrayResource("first time".getBytes()), false);

        Exception exception = assertThrows(FileExistException.class, () ->
                service.uploadFile(fileName, new ByteArrayResource("second time".getBytes()), false));

        Assert.isTrue(("File " + fileName + " has been existed").equals(exception.getMessage()), "File has been existed exception");
        Assert.isTrue("first time".equals(getFileContent(file)), "File is not over write");
    }

    @Test
    public void test_upload_with_duplicated_filename_overwrite() throws IOException {
        String fileName = "test_upload_with_duplicated_filename_overwrite.txt";
        File file = new File(fileUploadPath + "/" + fileName);

        service.uploadFile(fileName, new ByteArrayResource("first time".getBytes()), false);
        service.uploadFile(fileName, new ByteArrayResource("second time".getBytes()), true);

        Assert.isTrue("second time".equals(getFileContent(file)), "File is over write");
    }

    @Test
    public void test_delete_file_exist() {
        String fileName = "test_delete_file_exist.txt";
        File file = new File(fileUploadPath + "/" + fileName);

        service.uploadFile(fileName, new ByteArrayResource("test".getBytes()), false);
        Assert.isTrue(file.exists(), "File " + fileName + " is uploaded successfully");
        service.deleteFile(fileName);
        Assert.isTrue(!file.exists(), "File " + fileName + " is deleted successfully");
    }

    @Test
    public void test_delete_file_non_exist() {
        String fileName = "test_delete_file_non_exist.txt";
        File file = new File(fileUploadPath + "/" + fileName);

        Exception exception = assertThrows(FileNotExistException.class, () -> {
            service.deleteFile(fileName);
        });

        Assert.isTrue(("File " + fileName + " does not exist").equals(exception.getMessage()), "File not exist exception is thrown");
    }

    @Test
    public void test_list_files(){
        service.uploadFile("listedFile1.txt", new ByteArrayResource("aaa".getBytes()), false);
        service.uploadFile("listedFile2.txt", new ByteArrayResource("aaa".getBytes()), false);
        service.uploadFile("listedFile3.txt", new ByteArrayResource("aaa".getBytes()), false);
        service.uploadFile("listedFile4.txt", new ByteArrayResource("aaa".getBytes()), false);

        List<File> fileNames = service.listFile();

        Assert.isTrue(fileNames.stream().filter(t -> "listedFile1.txt".equals(t.getName())).count() == 1, "listedFile1.txt is in the list");
        Assert.isTrue(fileNames.stream().filter(t -> "listedFile2.txt".equals(t.getName())).count() == 1, "listedFile2.txt is in the list");
        Assert.isTrue(fileNames.stream().filter(t -> "listedFile3.txt".equals(t.getName())).count() == 1, "listedFile3.txt is in the list");
        Assert.isTrue(fileNames.stream().filter(t -> "listedFile4.txt".equals(t.getName())).count() == 1, "listedFile4.txt is in the list");
    }

    private String getFileContent(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        return new String(inputStream.readAllBytes());
    }

    @BeforeAll
    public static void init() {
        File path = new File(fileUploadPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        for (File file : path.listFiles()) {
            file.delete();
        }

        service = new FileStoreService(fileUploadPath);
    }
}