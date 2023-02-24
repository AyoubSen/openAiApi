package com.example.openaiApi.service;

import com.example.openaiApi.model.request.OpenAiRequest;
import com.example.openaiApi.model.request.OurMessage;
import com.example.openaiApi.model.response.OpenAiResponse;

public interface OpenAiService {
    OpenAiResponse askQuestion(OurMessage ourMessage);
}
