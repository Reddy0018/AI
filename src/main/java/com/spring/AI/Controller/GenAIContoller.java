package com.spring.AI.Controller;

import com.spring.AI.Service.ChatService;
import com.spring.AI.Service.GitHubService;
import com.spring.AI.Service.ImageService;
import com.spring.AI.Service.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/AI")
public class GenAIContoller {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ImageService imageResponse;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private GitHubService gitHubService;

    private String prompt = "";

    @GetMapping("/askAI")
    public String getResponse(@RequestParam(required = true) String prompt){
        return  chatService.sendMessage(prompt);
    }

    @GetMapping("/askAIOptions")
    public String getResponseOptions(@RequestParam(required = true) String prompt){
        return  chatService.sendMessageWithOptions(prompt);
    }

    @GetMapping("/generateImage")
    public void getResponseImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
         response.sendRedirect(imageResponse.generateImage(prompt).getResult().getOutput().getUrl());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParameter(MissingServletRequestParameterException ex) {
        String errorMessage = "Required parameter is missing: " + ex.getParameterName();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/getRecipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRecipeResponse(@RequestParam(required = true) String ingredients,
                                    @RequestParam(defaultValue = "any", required = false) String cuisine,
                                    @RequestParam(defaultValue = "No dietary Restrictions", required = false) String dietaryRestrictions,
                                    @RequestParam(defaultValue = "free", required = false) String version){
        var template = """
                I want to create a recipe using the following ingredients: {ingredients}
                The cuisine type I prefer is: {cuisine}.
                Please consider the following dietary restrictions: {dietaryRestrictions}.
                Please Provide me with detailed recipe including Title, list of Ingredients, & cooking steps.
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String,Object> params = Map.of("ingredients",ingredients,
                "cuisine",cuisine, "dietaryRestrictions",dietaryRestrictions);
        Prompt prompt = promptTemplate.create(params);

        if("free".equals(version)){
            System.out.println("In Free Version Method");
            return gitHubService.getResponse(prompt.getContents());
        }

        return recipeService.createRecipe(prompt);
    }
}
