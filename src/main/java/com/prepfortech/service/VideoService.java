package com.prepfortech.service;

import com.prepfortech.accessor.S3Accessor;
import com.prepfortech.accessor.VideoAccessor;
import com.prepfortech.accessor.models.VideoDTO;
import com.prepfortech.exception.InvalidVideoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoService {

    @Autowired
    VideoAccessor videoAccessor;

    @Autowired
    S3Accessor s3Accessor;

    public String getVideoUrl(final String videoId) {
        VideoDTO videoDTO = videoAccessor.getVideoByVideoId(videoId);
        if(videoDTO == null) {
            throw new InvalidVideoException("Video with videoId " + videoId + " does not exist!");
        }
        String videoPath = videoDTO.getVideoPath();

        return s3Accessor.getPreSignedUrl(videoPath, videoDTO.getTotalLength()*60);
    }

    public String getVideoThumbnail(final String videoId) {
        VideoDTO videoDTO = videoAccessor.getVideoByVideoId(videoId);
        if(videoDTO == null) {
            throw new InvalidVideoException("Video with videoId " + videoId + " does not exist!");
        }
        String thumbnailPath = videoDTO.getThumbnailPath();

        return s3Accessor.getPreSignedUrl(thumbnailPath, 2*60);
    }
}
