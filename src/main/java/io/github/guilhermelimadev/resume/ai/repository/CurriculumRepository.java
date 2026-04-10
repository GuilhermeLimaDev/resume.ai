package io.github.guilhermelimadev.resume.ai.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.guilhermelimadev.resume.ai.domain.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, UUID> {
}
