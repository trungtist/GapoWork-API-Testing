package gapowork.models.media;

import lombok.Data;

public @Data class VideoObject {
    private String category;
    private String file_type;
    private String file_link;
    private String id;
    private String name;
    private String quality;
    private Integer size;
    private String source;
    private UrlObject thumb_url;
    private String type;
    private UrlObject url;
    private String user_id;
    private String transcode_status;
}
