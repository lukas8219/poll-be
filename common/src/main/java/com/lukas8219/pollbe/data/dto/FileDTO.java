package com.lukas8219.pollbe.data.dto;

import com.lukas8219.pollbe.data.domain.FileStorage;
import lombok.Getter;

@Getter
public class FileDTO {

    private final byte[] file;
    private final String folderName;
    private final String fileName;

    public FileDTO(byte[] file, FileStorage fileStorage) {
        this.file = file;
        this.folderName = fileStorage.getFolder();
        this.fileName = fileStorage.getFileName();
    }

    public FileDTO(byte[] file, String folderName, String fileName) {
        this.file = file;
        this.folderName = folderName;
        this.fileName = fileName;
    }

}
