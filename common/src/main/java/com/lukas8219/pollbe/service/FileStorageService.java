package com.lukas8219.pollbe.service;

import com.lukas8219.pollbe.data.dto.FileDTO;

public interface FileStorageService {

    void save(FileDTO file);

    void saveOrOverwrite(FileDTO file);

    void delete(FileDTO file);

    String getLink(FileDTO file);

}
