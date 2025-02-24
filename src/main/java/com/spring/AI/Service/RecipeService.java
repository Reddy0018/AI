package com.spring.AI.Service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final ChatModel chatModel;

    @Autowired
    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(Prompt prompt){
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }
}
