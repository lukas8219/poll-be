package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.data.domain.User;
import com.lukas8219.pollbe.data.domain.UserPhoto;
import com.lukas8219.pollbe.data.dto.FileDTO;
import com.lukas8219.pollbe.data.interfaces.RequestFile;
import com.lukas8219.pollbe.exception.UserNotFoundException;
import com.lukas8219.pollbe.repository.UserPhotoRepository;
import com.lukas8219.pollbe.repository.UserRepository;
import com.lukas8219.pollbe.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPhotoEditServiceImpl implements UserPhotoEditService {

    private final UserRepository repository;
    private final UserPhotoRepository userPhotoRepository;

    private final FileStorageService fileStorageService;

    private final String DEFAULT_PHOTO_NAME = "profile-%s.%s";

    public UserPhoto edit(PollUserDetails userDetails, RequestFile file) {
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

        return userPhotoRepository.save(photo);
    }

    private FileDTO createFileDTO(RequestFile file, String folderName) {

        var bytes = file.getBytes();
        var extension = file.getExtension();
        var fileName = String.format(DEFAULT_PHOTO_NAME,
                UUID.randomUUID(), extension);
        return new FileDTO(bytes, folderName, fileName);
    }

    private void deleteUserPhoto(User user) {
        var photo = user.getPhoto();
        fileStorageService.delete(photo);
        userPhotoRepository.deleteByUserId(photo.getUser().getId());
    }
}
