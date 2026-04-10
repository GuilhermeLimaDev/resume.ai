package io.github.guilhermelimadev.resume.ai.infra;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.github.guilhermelimadev.resume.ai.service.PDFReaderService;

@Component
public class PDFReaderImpl implements PDFReaderService{

    @Override
    public String pdfReader(MultipartFile file){
        
        if(file.isEmpty()){
            throw new IllegalArgumentException("Arquivo Vazio");
        
        }
        try(PDDocument document = PDDocument.load(file.getInputStream())){
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);

        }
        catch(Exception e){
            throw new RuntimeException("Erro ao extrair o texto do PDF: ", e);
        }

    }
}
