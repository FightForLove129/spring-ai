package com.pain.springai.service;

import com.pain.springai.constant.Constants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author：tdpain
 * @Date: 2026/1/21
 */
@Service
public class SimpleAiQaService {

    private final ChatClient chatClient;
    private final PromptTemplate promptTemplate;

    @Autowired
    public SimpleAiQaService(ChatClient chatClient){
        this.chatClient = chatClient;
        // 创建一个提示模板，指导AI如何回答问题
        this.promptTemplate = new PromptTemplate(Constants.SIMPLE_AI_QA_TEMPLATE);
    }

    public String getAnswer(String question) {
        // 准备模板参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Constants.QUESTION_KEY, question);

        // 创建提示
        Prompt prompt = promptTemplate.create(parameters);

        // 调用AI获取回答
        return chatClient.prompt(prompt).call().content();
    }

}
