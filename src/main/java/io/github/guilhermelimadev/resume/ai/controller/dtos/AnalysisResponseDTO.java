package io.github.guilhermelimadev.resume.ai.controller.dtos;

import java.util.UUID;

import io.github.guilhermelimadev.resume.ai.domain.AnalysisStatus;

public record AnalysisResponseDTO(
    UUID id,
    String userName,
    String email,
    AnalysisStatus status,
    Long totalAIexecuteTime,
    CurriculumAnalysis content,
    String jobDescription
) {

}
