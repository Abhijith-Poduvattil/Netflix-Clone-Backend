package com.prepfortech.service;

import com.prepfortech.accessor.ProfileAccessor;
import com.prepfortech.accessor.models.ProfileType;
import com.prepfortech.accessor.models.UserDTO;
import com.prepfortech.controller.model.ProfileTypeInput;
import com.prepfortech.exception.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ProfileService {

    @Autowired
    ProfileAccessor profileAccessor;

    public void activateProfile(final String name, final ProfileTypeInput type) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        if(name.length() < 5 || name.length() > 20) {
            throw new InvalidDataException("Name length should be between 5 and 20");
        }
        profileAccessor.addNewProfile(userDTO.getUserId(), name, ProfileType.valueOf(type.name()));
    }

    public void deactivateProfile(final String profileID) {
        profileAccessor.deleteProfile(profileID);
    }
}
