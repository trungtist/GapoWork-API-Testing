package gapowork.models.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import gapowork.models.media.MediaObject;
import lombok.Data;

import java.util.List;

public @Data class PostObject {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    private String content;
    private List<MediaObject> media;
    private Integer content_rtf;
    private Integer privacy;
    private String target;

    // Object for create post with text content
    public PostObject(String content,  Integer content_rtf, Integer privacy, String target) {
        this.content = content;
        this.content_rtf = content_rtf;
        this.privacy = privacy;
        this.target = target;
    }

    // Object for create post with medias
    public PostObject(String content, List<MediaObject> media, Integer privacy, String target) {
        this.content = content;
        this.media = media;
        this.privacy = privacy;
        this.target = target;
    }

    // Object for update post
    public PostObject(String id, String content, List<MediaObject> media, Integer privacy) {
        this.id = id;
        this.content = content;
        this.media = media;
        this.privacy = privacy;
    }

    public PostObject() {
    }

}
