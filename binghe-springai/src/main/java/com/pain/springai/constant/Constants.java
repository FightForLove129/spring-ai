package com.pain.springai.constant;

/**
 * @Author：tdpain
 * @Description: 常量类
 * @Date: 2026/1/21
 */
public class Constants {
    public static final String QUESTION_KEY = "question";
    public static final String ANSWER_KEY = "answer";
    /**
     * 简单问答提示模板
     */
    public static final String SIMPLE_AI_QA_TEMPLATE = """
            你是一个智能问答助手，请简洁、准确地回答用户的问题。
            如果你不知道答案，请直接说不知道，不要编造信息。
            
            用户问题: {question}
            
            回答:
            """;

}
