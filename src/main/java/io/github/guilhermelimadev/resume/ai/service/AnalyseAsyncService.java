package io.github.guilhermelimadev.resume.ai.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import io.github.guilhermelimadev.resume.ai.controller.dtos.CurriculumAnalysis;
import io.github.guilhermelimadev.resume.ai.domain.Analysis;
import io.github.guilhermelimadev.resume.ai.domain.AnalysisStatus;
import io.github.guilhermelimadev.resume.ai.mapper.CurriculumAnalysisMapper;
import io.github.guilhermelimadev.resume.ai.repository.AnalysisRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AnalyseAsyncService {

    private final AiAssistentService aiAssistentService;
    private final AnalysisRepository analysisRepository;

    public AnalyseAsyncService(
        AiAssistentService aiAssistentService,
        AnalysisRepository analysisRepository) {
        this.aiAssistentService = aiAssistentService;
        this.analysisRepository = analysisRepository;
    }

    @Async
    public void process(UUID analysisID) {
        processInternal(analysisID);
    }

    @Transactional
    public void processInternal(UUID analysisID){
        StopWatch stopWatch = new StopWatch();
        Analysis analysis = analysisRepository.findById(analysisID).orElseThrow(
            () -> new EntityNotFoundException("Entidade não encontrada com id: "+ analysisID));
        analysis.setStatus(AnalysisStatus.PROCESSING);
        stopWatch.start();
        
        try{
            String curriculumContent = analysis.getCurriculum().getContent();
            String jobDescription = analysis.getJobDescription();
            CurriculumAnalysis result = aiAssistentService.curriculumAnalyze(userPrompt(curriculumContent,jobDescription ));
            analysis.setContent(CurriculumAnalysisMapper.toString(result));
            analysis.setStatus(AnalysisStatus.DONE);

       }catch(Exception e){
            analysis.setStatus(AnalysisStatus.ERROR);
            System.err.println("Erro ao processar analysis: " + analysisID + e);

       }finally{
            stopWatch.stop();   
            analysis.setAIexecutionTime(stopWatch.getTotalTimeMillis());
            analysisRepository.save(analysis);
       }
       
    }
    
    public String userPrompt(String curriculumContent, String jobDescription){
            String currentDate = LocalDate.now().toString();

            String userPrompt = """
                Analise o currículo abaixo levando em consideração a descrição da vaga 
                e gere a avaliação estruturada.

                Data Atual: %s

                Currículo:
                %s

                Descrição da Vaga:
                %s
                """.formatted(currentDate, curriculumContent, jobDescription);

        return userPrompt;
    }
}

