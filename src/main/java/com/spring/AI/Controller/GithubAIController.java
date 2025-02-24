package com.spring.AI.Controller;

import com.spring.AI.Service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/GitHubAI")
public class GithubAIController {

    @Autowired
    GitHubService gitHubService;

    @GetMapping("/sendRequestTest")
    public String Test(){
        return gitHubService.Test();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParameter(MissingServletRequestParameterException ex) {
        String errorMessage = "Required parameter is missing: " + ex.getParameterName();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getResponse")
    public String getResponse(@RequestParam(required = true) String prompt){
        return gitHubService.getResponse(prompt);
    }

}