package project.kolesa.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.kolesa.dto.AdDto;
import project.kolesa.model.Ad;
import project.kolesa.model.Image;
import project.kolesa.model.Model;
import project.kolesa.model.User;
import project.kolesa.repository.ImageRepository;
import project.kolesa.repository.ModelRepository;
import project.kolesa.services.AdService;
import project.kolesa.services.FileUploadService;
import project.kolesa.services.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private UserService userService;

    @Value("${loadURL}")
    private String myLoadUrl;

    @Value("${uploadUrl}")
    private String uploadUrl;

    @Autowired
    private AdService adService;
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Override
    public boolean uploadPhoto(MultipartFile file[], AdDto adDto) {
        try {

            for (MultipartFile multipartFile : file) {
                if (!(multipartFile.getContentType().equals("image/jpeg") || multipartFile.getContentType().equals("image/png"))) {
                    return false;
                }
            }
            User user = userService.getCurrentUser();
            Random random = new Random();
            List<String> fileNames = new ArrayList<>();
            for (MultipartFile multipartFile : file) {
                String fileName = DigestUtils.sha1Hex(user.getId() + "photo" + random.nextDouble() + System.currentTimeMillis()) + ".jpg";
                byte bytes[] = multipartFile.getBytes();
                Path path = Paths.get(uploadUrl +fileName);
                Files.write(path, bytes);
                fileNames.add(fileName);

            }

            System.out.println(fileNames);

            Ad ad = new Ad();
            ad.setId(adDto.getId());
            ad.setCity(adDto.getCity());
            ad.setModel(adDto.getModel());
            ad.setYearOfIssue(adDto.getYearOfIssue());
            ad.setPrice(adDto.getPrice());
            ad.setEngineType(adDto.getEngineType());
            ad.setMileage(adDto.getMileage());
            ad.setEngineVolume(adDto.getEngineVolume());
            ad.setColor(adDto.getColor());
            ad.setCarBody(adDto.getCarBody());
            ad.setGearbox(adDto.getGearbox());
            ad.setPhoto(fileNames.stream().findFirst().orElseThrow());
            ad.setAuthor(user);
            adService.saveAd(ad);
            imageRepository.saveAll(fileNames.stream().map(e -> new Image(e, ad)).toList());

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public byte[] getPhoto(String token) throws IOException {
        String picUrl = myLoadUrl;
        if (token!=null){
            picUrl = myLoadUrl +token;
        }
        InputStream in;
        try {
            ClassPathResource resource = new ClassPathResource(picUrl);
            in = resource.getInputStream();
        }catch (Exception e){
            picUrl = myLoadUrl;
            ClassPathResource resource = new ClassPathResource(picUrl);
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }
}
