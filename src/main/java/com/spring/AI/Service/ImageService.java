package com.spring.AI.Service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }
    private final OpenAiImageModel openAiImageModel;

    public ImageResponse generateImage(String prompt){
        System.out.println("In generateImage:: ");
        return openAiImageModel.call(new ImagePrompt(prompt));
    }

    public ImageResponse generateImageOptions(String prompt){
        return openAiImageModel.call(new ImagePrompt(prompt,
                OpenAiImageOptions.builder()
                        .quality("hd")
                        .N(4)
                        .height(1024)
                        .width(1024)
                        .build()));
    }
}
