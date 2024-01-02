package gapowork.models.media;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class AttachmentFileObject {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("image_id")
    @JsonAlias({"id","image_id","file_id", "video_id"})
    private String image_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("file_id")
    @JsonAlias({"id","image_id","file_id", "video_id"})
    private String file_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("video_id")
    @JsonAlias({"id","image_id","file_id", "video_id"})
    private String video_id;

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

    private String source;
}
