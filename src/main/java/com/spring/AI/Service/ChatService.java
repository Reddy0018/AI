package com.spring.AI.Service;


import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {

    private final ChatModel chatModel;

    @Value("${spring.ai.openai.image.options.model}")
    private String MODEL;

    @Autowired
    private GitHubService gitHubService;

    @Autowired
    ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public Map<String,String> handleError(Exception ex, String prompt) {
        Map<String,String> response = new HashMap<>();
        response.put("Paid Version Failed due to below reason", ex.getMessage());
        response.put("Response from Free Version", gitHubService.getResponse(prompt));
        return response;
    }

    public String sendMessage(String message) {
        return chatModel.call(message);
    }

    public String sendMessageWithOptions(String message) {
        try {
            return chatModel.call(new Prompt(message, OpenAiChatOptions.builder()
                    .model(MODEL)
                    .temperature(0.4)
                    .maxTokens(100)
                    .build())).getResult().getOutput().getContent();
        } catch (Exception e) {
            return handleError(e,message).toString();
        }
    }
}
