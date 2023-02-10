package project.kolesa.services;

import org.springframework.web.multipart.MultipartFile;
import project.kolesa.dto.AdDto;
import project.kolesa.model.Image;

import java.io.IOException;

public interface FileUploadService {
    boolean uploadPhoto(MultipartFile[] file, AdDto adDto);
    byte [] getPhoto(String token) throws IOException;
}
