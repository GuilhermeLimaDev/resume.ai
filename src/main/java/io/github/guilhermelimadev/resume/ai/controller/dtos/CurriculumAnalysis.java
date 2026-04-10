package io.github.guilhermelimadev.resume.ai.controller.dtos;

import java.util.List;

public record CurriculumAnalysis(
    String summary,
    List<String> strengths,
    List<String> improvements,
    List<String> suggestions,
    Ats ats,
    Integer finalScore
) {
    public record Ats(
        Integer compatibilityPercentage,
        List<String> missingKeywords,
        String rejectionRisk
    ) {}
}

