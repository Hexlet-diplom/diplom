package com.example.diplom.dto;

import com.example.diplom.model.MediaItem;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LessonDto {

    private Long id;
    private Long courseId;
    private Integer orderNumber;
    private String name;
    private String description;
    private String content;
    private List<MediaItem> media;

    public List<MediaItem> getMedia() {
        return media == null ? null : Collections.unmodifiableList(media);
    }

    public void setMedia(List<MediaItem> media) {
        this.media = media == null ? null : new ArrayList<>(media);
    }
}
