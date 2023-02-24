package com.example.openaiApi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

    @Data @AllArgsConstructor
    public class OpenAiResponse implements Serializable {
        private String id;
        private String object;
        private String model;
        private LocalDate created;
        private List<Choice> choices;
        private Usage usage;
    }
