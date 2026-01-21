package com.pain.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：tdpain
 * @Date: 2026/1/21
 */
@Service
public class SimpleAiConversationService {

    @Autowired
    private ChatClient chatClient;

    // TODO 此处仅为简单模拟，实际应为数据库或其他存储方式
    private final Map<String, List<Message>> conversations = new ConcurrentHashMap<>();

    public String getAnswer(String sessionId, String userMessage) {
        // 获取或创建会话历史
        List<Message> messageList = conversations.computeIfAbsent(sessionId, k -> new ArrayList<>());

        // 添加用户信息
        messageList.add(new UserMessage(userMessage));

        // 创建带有历史上下文的提示
        Prompt prompt = new Prompt(messageList);

        // 调用AI
        String response = chatClient.prompt(prompt).call().content();

        // 保存AI回复
        messageList.add(new AssistantMessage(response));

        // 管理会话长度，避免超出Token限制
        if (messageList.size() > 10) {
            messageList = messageList.subList(messageList.size() - 10, messageList.size());
            conversations.put(sessionId, messageList);
        }

        return response;
    }

    public void clearConversation(String sessionId) {
        conversations.remove(sessionId);
    }
}
