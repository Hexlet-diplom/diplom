package com.example.diplom.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Converter to serialize and deserialize a list of MediaItem objects
 * to and from a JSON string for database storage.
 */
@Converter
@NoArgsConstructor
public class MediaConverter implements AttributeConverter<List<MediaItem>, String> {

    /**
     * Jackson ObjectMapper instance for JSON serialization and deserialization.
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts the list of MediaItem objects to a JSON string for database storage.
     *
     * @param attribute the list of MediaItem objects
     * @return JSON string representation of the list
     */
    @Override
    public String convertToDatabaseColumn(final List<MediaItem> attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Media serialization error", e);
        }
    }

    /**
     * Converts the JSON string from the database back to a list of MediaItem objects.
     *
     * @param dbData the JSON string from the database
     * @return list of MediaItem objects
     */
    @Override
    public List<MediaItem> convertToEntityAttribute(final String dbData) {
        List<MediaItem> result = new ArrayList<>();
        try {
            if (dbData != null && !dbData.isBlank()) {
                result = mapper.readValue(dbData, new TypeReference<>() { });
            }
        } catch (IOException e) {
            throw new IllegalStateException("Media deserialization error", e);
        }
        return result;
    }
}
