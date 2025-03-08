package com.spring.AI.Service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Service
public class GitHubService {

    @Value("${GithubToken}")
    private String key;

    @Value("${GitHubEndPoint}")
    private String endpoint;

    @Value("${GitHubModel}")
    private String MODEL;

    private ChatCompletionsClient client = null;

    private void build(){
        if(null== this.client){
            client = new ChatCompletionsClientBuilder()
                    .credential(new AzureKeyCredential(key))
                    .endpoint(endpoint)//.retryOptions(new RetryOptions(new FixedDelayOptions(5, Duration.ofSeconds(10))))
                    .buildClient();
        }
    }

    @GetMapping("/sendRequestTest")
    public String Test(){
        build();
        List<ChatRequestMessage> chatMessages = Arrays.asList(
                new ChatRequestUserMessage("Recipe for Brownie?")
        );
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel(MODEL);
        ChatCompletions completions = client.complete(chatCompletionsOptions);
        return  completions.getChoice().getMessage().getContent();
    }

    public String getResponse(String prompt){
        System.out.println("In GitHub Free Version Method:: "+ prompt);
        build();
        BinaryData binaryData = BinaryData.fromString(prompt);
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(Arrays.asList(
                new ChatRequestUserMessage(binaryData)
        ));
        chatCompletionsOptions.setTemperature(0.5d);
        chatCompletionsOptions.setModel(MODEL);

        ChatCompletions completions = client.complete(chatCompletionsOptions);
        return  completions.getChoice().getMessage().getContent();
    }
}
