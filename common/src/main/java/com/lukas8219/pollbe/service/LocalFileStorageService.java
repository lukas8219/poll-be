package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.domain.FileStorage;
import com.lukas8219.pollbe.data.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class LocalFileStorageService implements FileStorageService {


    @Value("${resource-server}")
    private String resourceServer;

    @Override
    public void save(FileDTO file) {
        try {
            var path = formatPathFor(file.getFolderName(), file.getFileName());
            var result = Paths.get(path);

            new File(createFormatFolder(file.getFolderName()))
                    .mkdirs();

            Files.write(result, file.getFile());
        } catch (Exception e) {
            log.error("An error occurred when trying to save File to LocalFileStorage", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveOrOverwrite(FileDTO file) {

    }

    @Override
    public void delete(FileDTO file) {
        var path = String.format("%s/%s", file.getFolderName(), file.getFileName());
        var result = new File(path);
        if (result.delete()) {
            log.info("{} deleted successfuly", path);
        } else {
            log.error("Unable to delete file {}", path);
        }
    }

    @Override
    public String getLink(FileDTO file) {
        return null;
    }

    @Override
    public void delete(FileStorage file) {

    }

    @Override
    public String getLink(FileStorage file) {
        return Paths.get(String.format("%s/%s", resourceServer, formatPathFor(file.getFolder(), file.getFileName()))).toString();
    }

    public String formatPathFor(String folderName, String fileName) {
        return String.format("%s/%s", createFormatFolder(folderName), fileName);
    }

    public String createFormatFolder(String folderName) {
        return String.format("%s/%s", "userPhotos", folderName);
    }
}
