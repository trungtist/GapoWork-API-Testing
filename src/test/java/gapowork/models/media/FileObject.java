package gapowork.models.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileObject {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("user_id")
    private String user_id;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("url")
    private UrlObject url;

    @JsonProperty("file_type")
    private String file_type;
    @JsonProperty("file_link")
    private String file_link;
}
