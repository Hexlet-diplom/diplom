package com.example.diplom.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MediaItemDto {
    private String type;
    private String url;
    private String alt;
    private String caption;
    private String content;
    private String language;
    private int position;
}
