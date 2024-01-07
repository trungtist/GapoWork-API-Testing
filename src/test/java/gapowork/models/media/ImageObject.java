package gapowork.models.media;

import lombok.Data;

public @Data class ImageObject {
    private String id;
    private String user_id;
    private String src;
    private UrlObject url;
    private String type;
    private String file_type;
    private String source;
    private String category;
    private Integer size;
    private Integer width;
    private Integer height;
    private String quality;
    private String name;
}
