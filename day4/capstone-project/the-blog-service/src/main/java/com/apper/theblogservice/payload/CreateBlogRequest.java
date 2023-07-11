package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBlogRequest {

    @JsonProperty("title")
    @NotBlank(message = "Title is required")
    private String title;

    @JsonProperty("body")
    @NotBlank(message = "body is required")
    private String body;


    @JsonProperty("blogger_id")
    @NotBlank(message = "blogger_id is required")
    private String bloggerId;
}
