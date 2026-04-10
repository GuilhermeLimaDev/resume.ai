package io.github.guilhermelimadev.resume.ai.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.guilhermelimadev.resume.ai.controller.dtos.CurriculumAnalysis;

public final class CurriculumAnalysisMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private CurriculumAnalysisMapper() {
    }

    public static String toString(CurriculumAnalysis content) {
        try {
            return MAPPER.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(
                "Erro ao serializar CurriculumAnalysis para JSON",
                e
            );
        }
    }

    public static CurriculumAnalysis toCurriculumAnalysis(String content) {
        validateJson(content);

        try {
            return MAPPER.readValue(content, CurriculumAnalysis.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                "JSON inválido ou incompatível com CurriculumAnalysis",
                e
            );
        }
    }

    private static void validateJson(String json) {
        try {
            MAPPER.readTree(json);
        } catch (Exception e) {
            throw new IllegalArgumentException("Conteúdo não é um JSON válido", e);
        }
    }
}
