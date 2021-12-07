package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.dto.FileDTO;
import org.springframework.stereotype.Service;

@Service
public class LocalFileStorageService implements FileStorageService {

    @Override
    public void save(FileDTO file) {

    }

    @Override
    public void saveOrOverwrite(FileDTO file) {

    }

    @Override
    public void delete(FileDTO file) {

    }

    @Override
    public String getLink(FileDTO file) {
        return null;
    }
}
