package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.config.ApplicationConfiguration;
import com.lukas8219.pollbe.data.domain.FileStorage;
import com.lukas8219.pollbe.data.dto.FileDTO;
import com.lukas8219.pollbe.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocalFileStorageService implements FileStorageService {

    private final ApplicationConfiguration configuration;

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
            throw new UnprocessableEntityException("Não foi possível salvar seu arquivo.");
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
        return formatByUri(file.getFolderName(), file.getFolderName());
    }

    @Override
    public void delete(FileStorage file) {

    }

    @Override
    public String getLink(FileStorage file) {
        return formatByUri(file.getFolder(), file.getFileName());
    }

    private String formatByUri(String folderName, String fileName) {
        return URI.create(String.format("%s/%s", configuration.getResourceServer(), formatPathFor(folderName, fileName))).toString();
    }

    private String formatPathFor(String folderName, String fileName) {
        return String.format("%s/%s", createFormatFolder(folderName), fileName);
    }

    private String createFormatFolder(String folderName) {
        return String.format("%s/%s", "userPhotos", folderName);
    }
}
