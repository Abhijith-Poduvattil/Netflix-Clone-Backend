package com.prepfortech.controller.model;

import com.prepfortech.accessor.models.ShowAudience;
import com.prepfortech.accessor.models.ShowGenre;
import com.prepfortech.accessor.models.ShowType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShowOutput {
    private String showId;
    private String name;
    private ShowType typeOfShow;
    private ShowGenre genre;
    private ShowAudience audience;
    private double rating;
    private int length;
    private String thumbnailLink;
}
