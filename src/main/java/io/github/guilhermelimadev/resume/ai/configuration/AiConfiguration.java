package io.github.guilhermelimadev.resume.ai.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import io.github.guilhermelimadev.resume.ai.service.AiAssistentService;

@Configuration
public class AiConfiguration {

    @Value("${gemini.api-key}")
    private String apiKey;
    
    @Value("${gemini.model}")
    private String modelName;
    
    @Bean
    public GoogleAiGeminiChatModel googleAiGeminiChatModel(){
        return GoogleAiGeminiChatModel.builder()
        .apiKey(apiKey)
        .modelName(modelName)
        .build();
    }

    @Bean
    public ChatMemory chatMemory(){
        return MessageWindowChatMemory.builder()
        .maxMessages(10)
        .build();
    }

    @Bean
    public AiAssistentService  assistant(GoogleAiGeminiChatModel model, ChatMemory chatMemory) {
        return AiServices.builder(AiAssistentService .class)
                .chatModel(model)
                .chatMemory(chatMemory)
                .build();
    }
}
