package io.github.guilhermelimadev.resume.ai.domain;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 10024)
    private String content;
    @Column(length = 10024)
    private String jobDescription;
    @JoinColumn(name = "curriculum_id")
    @ManyToOne(optional = false)
    private Curriculum curriculum;
    @Enumerated(EnumType.STRING)
    private AnalysisStatus status;
    private Long AIexecutionTime;
}
