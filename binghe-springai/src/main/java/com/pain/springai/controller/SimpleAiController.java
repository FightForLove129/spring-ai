package com.pain.springai.controller;

import com.pain.springai.constant.Constants;
import com.pain.springai.request.MessageRequest;
import com.pain.springai.service.SimpleAiQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author：tdpain
 * @Date: 2026/1/21
 */
@RequestMapping("/api/simple/ai")
@RestController
public class SimpleAiController {

    @Autowired
    private SimpleAiQaService simpleAiQaService;

    @PostMapping("/ask")
    public Map<String, String> askQuestion(@RequestBody MessageRequest request) {
        String answer = simpleAiQaService.getAnswer(request.getQuestion());
        Map<String, String> response = new HashMap<>();
        response.put(Constants.QUESTION_KEY, request.getQuestion());
        response.put(Constants.ANSWER_KEY, answer);
        return response;
    }
}
