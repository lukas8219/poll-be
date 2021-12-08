package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.domain.UserPhoto;
import com.lukas8219.pollbe.data.dto.FileDTO;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import com.lukas8219.pollbe.data.mapper.UserMapper;
import com.lukas8219.pollbe.exception.UnprocessableEntityException;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.repository.UserPhotoRepository;
import com.lukas8219.pollbe.repository.UserRepository;
import com.lukas8219.pollbe.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPhotoEditService {

    private final UserRepository repository;
    private final UserPhotoRepository userPhotoRepository;
    private final UserMapper mapper;

    private final FileStorageService fileStorageService;

    private final String DEFAULT_PHOTO_NAME = "profile.%s";

    @Transactional
    public UserPhotoDTO edit(PollUserDetails userDetails, MultipartFile file) {
        var user = repository.findById(userDetails.getId()).orElseThrow(UserNotFoundException::new);

        if (user.getPhoto() != null) {
            deleteUserPhoto(user);
        }

        var dto = createFileDTO(file, userDetails.getId().toString());

        var photo = new UserPhoto();
        photo.setUser(user);
        photo.setFileName(dto.getFileName());
        photo.setFolder(dto.getFolderName());
        photo.setUploadedAt(LocalDateTime.now());

        fileStorageService.save(dto);
        userPhotoRepository.save(photo);

        return mapper.toPhotoDTO(photo);
    }

    private FileDTO createFileDTO(MultipartFile file, String folderName) {
        try {
            var bytes = file.getBytes();
            var extension = getExtension(file);
            var fileName = String.format(DEFAULT_PHOTO_NAME, extension);
            return new FileDTO(bytes, folderName, fileName);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new UnprocessableEntityException("Não foi possível salvar seu arquivo");
        }
    }

    private String getExtension(MultipartFile file) {
        var original = file.getOriginalFilename();
        var idx = original.lastIndexOf(".")+1;
        return original.substring(idx);
    }

    private void deleteUserPhoto(User user) {
        var photo = user.getPhoto();
        fileStorageService.delete(photo);
        userPhotoRepository.deleteByUserId(photo.getUser().getId());
    }
}
