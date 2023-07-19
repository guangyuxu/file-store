package com.woven.gavin.fs.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel
public class FileDto {
    private String fileName;
    private long size;
    private long lastModified;
}
