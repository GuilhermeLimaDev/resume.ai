package io.github.guilhermelimadev.resume.ai.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import io.github.guilhermelimadev.resume.ai.controller.dtos.CurriculumAnalysis;

@AiService
public interface AiAssistentService {

 @SystemMessage("""
         Você é um especialista em recrutamento técnico e ATS.

          Regras OBRIGATÓRIAS:
          - Responda exclusivamente em JSON válido
          - Nunca inclua explicações, comentários ou markdown
          - Sempre preencha TODOS os campos
          - Use arrays vazios quando não houver dados
          - Pontuações devem ser de 0 a 100

         """)
       CurriculumAnalysis curriculumAnalyze(@UserMessage String curriculumContext);


} 
