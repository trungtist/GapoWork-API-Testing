package gapowork.models.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.junit.Ignore;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class FileObject {
    private String id;
    private String name;
    private String user_id;
    private Integer size;
    private UrlObject url;
    private String file_type;
    private String file_link;
}
