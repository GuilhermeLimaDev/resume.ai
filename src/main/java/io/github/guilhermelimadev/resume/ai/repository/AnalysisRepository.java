 package io.github.guilhermelimadev.resume.ai.repository;
 import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.guilhermelimadev.resume.ai.domain.Analysis;

 public interface AnalysisRepository extends JpaRepository<Analysis, UUID> {
 }
