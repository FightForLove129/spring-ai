package com.pain.springai.service;

import com.pain.springai.constant.Constants;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：tdpain
 * @Date: 2026/1/21
 */
@Service
public class SimpleAiKnowledgeService {
    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private PromptTemplate promptTemplate;
    @Autowired
    private ChatClient chatClient;

    public String getAnswer(String question) {
        // 在知识库中搜索相关文档
        List<Document> relevantDocs = vectorStore.similaritySearch(question);
        if (relevantDocs == null) {
            relevantDocs = new ArrayList<>();
        }
        // 构建上下文
        StringBuilder context = new StringBuilder();
        for (Document doc : relevantDocs) {
            context.append(doc.getText()).append("\n\n");
        }

        // 创建提示模板
        PromptTemplate promptTemplate = new PromptTemplate(Constants.SIMPLE_AI_QA_TEMPLATE);

        // 准备参数
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Constants.CONTEXT_KEY, context.toString());
        parameters.put(Constants.QUESTION_KEY, question);
        // 创建提示并调用AI
        Prompt prompt = promptTemplate.create(parameters);
        return chatClient.prompt(prompt).call().content();
    }

}
