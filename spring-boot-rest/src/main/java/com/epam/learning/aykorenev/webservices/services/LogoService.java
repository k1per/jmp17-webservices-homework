package com.epam.learning.aykorenev.webservices.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
public interface LogoService  {
    void saveLogo(MultipartFile file, Long userId);
    InputStream getLogoAsStream(Long userId);
}
