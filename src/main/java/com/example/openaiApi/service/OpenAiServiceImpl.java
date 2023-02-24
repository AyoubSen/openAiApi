package com.example.openaiApi.service;



import com.example.openaiApi.config.OpenAiConfig;
import com.example.openaiApi.model.request.OpenAiRequest;
import com.example.openaiApi.model.request.OurMessage;
import com.example.openaiApi.model.response.OpenAiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<OpenAiRequest> buildHttpEntity(OpenAiRequest msgRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(OpenAiConfig.MEDIA_TYPE));
        headers.add(OpenAiConfig.AUTHORIZATION, OpenAiConfig.BEARER + OpenAiConfig.API_KEY);
        return new HttpEntity<>(msgRequest, headers);
    }

    public OpenAiResponse getResponse(HttpEntity<OpenAiRequest> chatRequestHttpEntity) {
        ResponseEntity<OpenAiResponse> responseEntity = restTemplate.postForEntity(
                OpenAiConfig.URL,
                chatRequestHttpEntity,
                OpenAiResponse.class);

        return responseEntity.getBody();
    }

    public OpenAiResponse askQuestion(OurMessage ourMessage) {
        System.out.println(ourMessage);
        return this.getResponse(
                this.buildHttpEntity(
                        new OpenAiRequest(
                                OpenAiConfig.MODEL,
                                ourMessage.getMessage(),
                                OpenAiConfig.TEMPERATURE,
                                OpenAiConfig.MAX_TOKEN)));

    }
}