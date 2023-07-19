package com.woven.gavin.fs.client.command;

import com.woven.gavin.fs.client.service.ClientService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.client.HttpStatusCodeException;

@RequiredArgsConstructor
@ShellComponent
@ShellCommandGroup("File Storage")
public class ClientCommand {

    private final ClientService service;

    @ShellMethod(key = "file-upload", value = "file-upload <fileName with full path>")
    public String uploadFile(@ShellOption("fileName") String fileName) {
        try{
            return service.uploadFile(fileName);
        } catch (HttpStatusCodeException ex) {
            return ex.getMessage();
        }
    }

    @ShellMethod(key = "file-list", value = "Show file list of all the existing files")
    public List<String> listFile() {
        try{
            return service.listFile();
        } catch (HttpStatusCodeException ex) {
            return Collections.singletonList(ex.getMessage());
        }
    }

    @ShellMethod(key = "file-delete", value = "file-delete <fileName>")
    public String deleteFile(@ShellOption("filename") String fileName) {
        try{
            return service.deleteFile(fileName);
        } catch (HttpStatusCodeException ex) {
            return ex.getMessage();
        }
    }
}

