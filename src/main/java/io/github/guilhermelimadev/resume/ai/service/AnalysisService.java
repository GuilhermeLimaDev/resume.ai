package io.github.guilhermelimadev.resume.ai.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import io.github.guilhermelimadev.resume.ai.controller.dtos.AnalysisResponseDTO;
import io.github.guilhermelimadev.resume.ai.controller.dtos.CurriculumAnalysis;
import io.github.guilhermelimadev.resume.ai.controller.dtos.UserDTO;
import io.github.guilhermelimadev.resume.ai.domain.Analysis;
import io.github.guilhermelimadev.resume.ai.domain.AnalysisStatus;
import io.github.guilhermelimadev.resume.ai.domain.Curriculum;
import io.github.guilhermelimadev.resume.ai.mapper.CurriculumAnalysisMapper;
import io.github.guilhermelimadev.resume.ai.repository.AnalysisRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AnalysisService {

    private final PDFReaderService pdfReaderService;
    private final AnalyseAsyncService analyseAsyncService;
    private final CurriculumService curriculumService;
    private final AnalysisRepository analysisRepository;

    public AnalysisService(
        PDFReaderService pdfReaderService, 
        AnalyseAsyncService analyseAsyncService,
        CurriculumService curriculumService,
        AnalysisRepository analysisRepository) {
        this.pdfReaderService = pdfReaderService;
        this.analyseAsyncService = analyseAsyncService;
        this.curriculumService = curriculumService;
        this.analysisRepository = analysisRepository;
    }

    @Transactional
    public UUID start(MultipartFile curriculumFile, UserDTO data){
        String curriculumContent = pdfReaderService.pdfReader(curriculumFile);
        Curriculum curriculum = curriculumService.create(data.email(), curriculumContent, data.name());
        Analysis analysis = create(curriculum, data.jobDescription());

        TransactionSynchronizationManager.registerSynchronization(
            new TransactionSynchronization() {
            @Override
            public void afterCommit(){
                analyseAsyncService.process(analysis.getId());
            }
        });
        
        return analysis.getId();
    }

    @Transactional
    public Analysis create(Curriculum curriculum, String jobDescription){
        Analysis analysis = new Analysis();
        analysis.setCurriculum(curriculum);
        analysis.setJobDescription(jobDescription);
        analysis.setStatus(AnalysisStatus.PENDING);
        return analysisRepository.save(analysis);
    }

    public AnalysisResponseDTO getAnalysisById(UUID id){
        Analysis analysis = analysisRepository.findById(id).orElseThrow(
            ()-> new EntityNotFoundException("Analise não encontrada com id: "+ id));
            CurriculumAnalysis dto = null;
            
            if(analysis.getStatus() == AnalysisStatus.DONE){
                 dto = CurriculumAnalysisMapper.toCurriculumAnalysis(analysis.getContent());
            }
           
        return new AnalysisResponseDTO(
            analysis.getId(), 
            analysis.getCurriculum().getName(),
            analysis.getCurriculum().getEmail(),
            analysis.getStatus(), 
            analysis.getAIexecutionTime(),
            dto,
            analysis.getJobDescription()
        );
    }
}
