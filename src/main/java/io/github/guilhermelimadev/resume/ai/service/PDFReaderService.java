package io.github.guilhermelimadev.resume.ai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PDFReaderService {

    String pdfReader(MultipartFile curriculum);
}
