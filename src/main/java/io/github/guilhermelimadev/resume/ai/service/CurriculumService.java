package io.github.guilhermelimadev.resume.ai.service;

import org.springframework.stereotype.Service;

import io.github.guilhermelimadev.resume.ai.domain.Curriculum;
import io.github.guilhermelimadev.resume.ai.repository.CurriculumRepository;
import jakarta.transaction.Transactional;

@Service
public class CurriculumService {

   private final CurriculumRepository curriculumRepository;

   public CurriculumService(CurriculumRepository curriculumRepository){
        this.curriculumRepository = curriculumRepository;
   }

   @Transactional
   public Curriculum create(String email, String content, String userName){
        Curriculum curriculum = new Curriculum();
        curriculum.setEmail(email);
        curriculum.setContent(content);
        curriculum.setName(userName);

    return curriculumRepository.save(curriculum);
   }
}
