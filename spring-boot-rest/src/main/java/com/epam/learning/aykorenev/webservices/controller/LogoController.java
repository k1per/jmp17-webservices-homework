package com.epam.learning.aykorenev.webservices.controller;

import com.epam.learning.aykorenev.webservices.services.LogoService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
@RestController
@RequestMapping("/users")
public class LogoController {

    private final LogoService logoService;

    @Autowired
    public LogoController(LogoService logoService) {
        this.logoService = logoService;
    }

    @RequestMapping(path = "/{userId}/logo", method = RequestMethod.PUT)
    public void uploadUserLogo(@RequestParam(name = "file.jpg") MultipartFile file,
                               @PathVariable("userId") Long userId){
        logoService.saveLogo(file, userId);
    }

    @RequestMapping(path = "/{userId}/logo", method = RequestMethod.GET)
    public void getUserLogo(@PathVariable("userId") Long userId,
                            HttpServletResponse response) throws Exception{
        InputStream logo = logoService.getLogoAsStream(userId);
        response.addHeader("Content-disposition", "attachment;filename=logo.jpg");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(logo, response.getOutputStream());
        response.flushBuffer();
    }
}
