package io.github.guilhermelimadev.resume.ai.controller;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.guilhermelimadev.resume.ai.controller.dtos.AnalysisResponseDTO;
import io.github.guilhermelimadev.resume.ai.controller.dtos.UserDTO;
import io.github.guilhermelimadev.resume.ai.service.AnalysisService;


@RestController
@RequestMapping("/api/v1/")
public class AnalyzerController {

    private final AnalysisService analysisService;
    
    public AnalyzerController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "analyzer")
    public UUID analyzer(@RequestPart("curriculum") MultipartFile curriculum, @RequestPart("data") UserDTO data){
        return analysisService.start(curriculum, data);
    }

    @GetMapping("analysis/{id}")
    public AnalysisResponseDTO get(@PathVariable("id") UUID id) throws Exception{
        return analysisService.getAnalysisById(id);
    }
}
