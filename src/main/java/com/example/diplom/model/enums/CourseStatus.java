package com.example.diplom.model.enums;

public enum CourseStatus {
    /**
     * Course is in draft mode, not visible to users.
     */
    DRAFT,

    /**
     * Course is opened and available for enrollment.
     */
    OPENED,

    /**
     * Course is announced but not yet available.
     */
    COMING_SOON,

    /**
     * Course is archived and no longer active.
     */
    ARCHIVED,
}
