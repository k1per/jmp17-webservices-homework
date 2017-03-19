package com.epam.learning.aykorenev.webservices.services;

import com.epam.learning.aykorenev.webservices.exceptions.CouldNotReadLogoException;
import com.epam.learning.aykorenev.webservices.exceptions.CouldNotSaveLogoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
@Component
public class FileLogoService implements LogoService {

    @Value("${logo.directory}")
    private String destinationFile;
    private static final String logoName = "logo.jpg";

    @Override
    public void saveLogo(MultipartFile multipartFile, Long userId) {
        File pathFile = new File(destinationFile + "/" + userId);
        if (!pathFile.exists()) {
            pathFile.mkdir();
        }
        pathFile = new File(pathFile + "/" + logoName);
        try {
            multipartFile.transferTo(pathFile);
        } catch (IOException e) {
            throw new CouldNotSaveLogoException("Could not save logo to directory " + pathFile);
        }
    }

    @Override
    public InputStream getLogoAsStream(Long userId) {
        String filePath = destinationFile + "/" + userId + "/" + logoName;
        try {
            return Files.newInputStream(Paths.get(filePath));
        } catch (IOException e) {
            throw new CouldNotReadLogoException("Could not read logo from directory " + filePath);
        }
    }
}
