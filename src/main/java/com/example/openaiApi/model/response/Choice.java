package com.example.openaiApi.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data @AllArgsConstructor
public class Choice implements Serializable {
    private Integer index;
    private String text;
    private Integer logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;
}


