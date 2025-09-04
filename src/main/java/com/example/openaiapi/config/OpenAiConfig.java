package com.example.openaiapi.config;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAI SDK에서 쓸 수 있는 OpenAI 객체를 Bean으로 등록
 * */
@RequiredArgsConstructor
@Configuration
public class OpenAiConfig {

    private final OpenAiProperties openAiProperties;

    @Bean
    public OpenAIClient openAIClient() {
        // OpenAI SDK(Software Development Kit) 클라이언트 생성
        return OpenAIOkHttpClient.builder()
                .apiKey(openAiProperties.getApiKey()) // API 키 주입
                .baseUrl(openAiProperties.getApiUrl())
                .build();
    }
}
