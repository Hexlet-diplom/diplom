package com.example.diplom.mapper;

import com.example.diplom.dto.MediaItemDto;
import com.example.diplom.model.MediaItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MediaItemMapper {

    public MediaItemDto toDto(MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }

        return new MediaItemDto(
                mediaItem.getType(),
                mediaItem.getUrl(),
                mediaItem.getAlt(),
                mediaItem.getCaption(),
                mediaItem.getContent(),
                mediaItem.getLanguage(),
                mediaItem.getPosition()
        );
    }

    public MediaItem toEntity(MediaItemDto dto) {
        if (dto == null) {
            return null;
        }

        return new MediaItem(
                dto.getType(),
                dto.getUrl(),
                dto.getAlt(),
                dto.getCaption(),
                dto.getContent(),
                dto.getLanguage(),
                dto.getPosition()
        );
    }

    public List<MediaItemDto> toDtoList(List<MediaItem> mediaItems) {
        if (mediaItems == null) {
            return null;
        }

        return mediaItems.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<MediaItem> toEntityList(List<MediaItemDto> dtoList) {
        if (dtoList == null) {
            return null;
        }

        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
