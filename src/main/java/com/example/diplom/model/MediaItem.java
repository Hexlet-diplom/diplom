package com.example.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a media item associated with a lesson.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaItem {

    /**
     * The type of media (e.g., "image", "video", "audio").
     */
    private String type;

    /**
     * The URL where the media is located.
     */
    private String url;

    /**
     * Alternative text for the media, used for accessibility.
     */
    private String alt;

    /**
     * Caption describing the media item.
     */
    private String caption;

    /**
     * Additional content or description related to the media.
     */
    private String content;

    /**
     * The language of the media content.
     */
    private String language;

    /**
     * The position or order of the media item within a list.
     */
    private int position;
}

