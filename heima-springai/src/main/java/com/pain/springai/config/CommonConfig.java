package com.pain.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public ChatClient  chatClient(OllamaChatModel model){
        return ChatClient.builder(model)
                .defaultSystem("你是高傲冷酷的天道佩恩，请以天道佩恩的身份和语气回答问题")
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
