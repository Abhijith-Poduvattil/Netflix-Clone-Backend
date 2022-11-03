package com.prepfortech.validator;

import com.prepfortech.accessor.ProfileAccessor;
import com.prepfortech.accessor.VideoAccessor;
import com.prepfortech.accessor.models.ProfileDTO;
import com.prepfortech.accessor.models.VideoDTO;
import com.prepfortech.exception.InvalidProfileException;
import com.prepfortech.exception.InvalidVideoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    @Autowired
    private ProfileAccessor profileAccessor;

    @Autowired
    private VideoAccessor videoAccessor;

    public void validateProfile(final String profileId, final String userId) {
        ProfileDTO profileDTO = profileAccessor.getProfileByProfileId(profileId);
        if(profileDTO == null || !profileDTO.getUserId().equals(userId)) {
            throw new InvalidProfileException("Profile " + profileId + " is invalid or doesnt exist!");
        }
    }

    public void validateVideoId(final String videoId) {
        VideoDTO videoDTO = videoAccessor.getVideoByVideoId(videoId);
        if(videoDTO == null) {
            throw new InvalidVideoException("Video with videoId " + videoId + " does not exist!");
        }
    }
}
