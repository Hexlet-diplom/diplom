package com.example.diplom.helper;

import com.example.diplom.model.MediaItem;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Helper class for processing media items and generating HTML snippets.
 */
@Component("mediaHelper") // имя важно для Thymeleaf
public final class MediaHelper {

    private MediaHelper() {
        // Utility constructor
    }

    /**
     * Generates HTML for an image media item.
     *
     * @param media the media item representing an image
     * @return HTML string for the image
     */
    public static String getImageHtml(final MediaItem media) {
        return String.format("<div class='image-item'><img src='%s' alt='%s'><div class='image-caption'>%s</div></div>",
                media.getUrl(), media.getAlt(), media.getCaption());
    }

    /**
     * Generates HTML for a code example media item.
     *
     * @param media the media item representing a code example
     * @return HTML string for the code example
     */
    public static String getCodeHtml(final MediaItem media) {
        return String.format("<div class='code-example'><pre><code class='language-%s'>%s</code></pre></div>",
                media.getLanguage(), media.getContent());
    }

    /**
     * Processes the content by replacing media placeholders with corresponding HTML.
     *
     * @param content the original content containing media placeholders
     * @param mediaItems the list of media items to be inserted
     * @return processed content with media placeholders replaced by HTML
     */
    public static String processContent(final String content, final List<MediaItem> mediaItems) {
        String processed = content;
        for (int i = 0; i < mediaItems.size(); i++) {
            final MediaItem media = mediaItems.get(i);
            final String replacement = "IMAGE".equals(media.getType())
                    ? getImageHtml(media)
                    : getCodeHtml(media);
            processed = processed.replace("[[MEDIA:" + (i + 1) + "]]", replacement);
        }
        return processed;
    }
}
