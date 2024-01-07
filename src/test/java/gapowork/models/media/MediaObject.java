package gapowork.models.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class MediaObject {

    // Common
    private String id;
    private String name;;
    private String category;
    private String quality;
    private String user_id;
    private UrlObject url;
    private String source;
    private String type;
    private Integer size;

    // Images
    private Integer width;
    private Integer height;
    private String src;

    // Videos
    private String video_id;
    private String file_type;
    private String file_link;

    // Background
    private String font_color;

    public MediaObject() {

    }

}
