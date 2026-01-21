package com.pain.springai.request;

import lombok.Data;

/**
 * @Author：tdpain
 * @Date: 2026/1/21
 */
@Data
public class MessageRequest {
    private String question;
    private String sessionId;
}
