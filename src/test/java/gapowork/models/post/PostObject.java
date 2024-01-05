package gapowork.models.post;

import lombok.Data;

public @Data class PostObject {
    private String content;
    private Integer content_rtf;
    private Integer privacy;
    private String target;

    public PostObject(String content,  Integer content_rtf, Integer privacy, String target) {
        this.content = content;
        this.content_rtf = content_rtf;
        this.privacy = privacy;
        this.target = target;
    }
}
