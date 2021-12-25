package com.lukas8219.pollbe.service.user;

import com.lukas8219.pollbe.data.domain.PollUserDetails;
import com.lukas8219.pollbe.factory.RequestFileFactory;
import com.lukas8219.pollbe.data.dto.UserDTO;
import com.lukas8219.pollbe.data.dto.UserEditDTO;
import com.lukas8219.pollbe.data.dto.UserPhotoDTO;
import com.lukas8219.pollbe.data.mapper.UserMapper;
import com.lukas8219.pollbe.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFacadeImpl implements UserFacade {

    private final UserEditService editService;
    private final UserPhotoEditService photoEditService;
    private final UserGetService getService;

    private final UserMapper mapper;

    public UserDTO edit(PollUserDetails userDetails, UserEditDTO dto) {
        var result = editService.edit(userDetails, dto);
        return mapper.toDTO(result);
    }

    public UserPhotoDTO editPhoto(PollUserDetails userDetails, MultipartFile file) {
        try {
            var dto = photoEditService.edit(userDetails, RequestFileFactory.of(file));
            return mapper.toPhotoDTO(dto);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new UnprocessableEntityException("Não foi possível salvar seu arquivo");
        }
    }

    public UserDTO getUser(PollUserDetails userDetails) {
        var dto = getService.getUser(userDetails.getId());
        return mapper.toDTO(dto);
    }
}
