package com.itechart.trucking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.trucking.domain.Letter;
import com.itechart.trucking.service.ImageService;
import com.itechart.trucking.service.LetterService;
import com.mysql.jdbc.Blob;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Optional;

/**
 * author Vlad Sytyi
 * 18.12.2017
 */
@RestController
@RequestMapping("/api/image")
public class FileUploadController {


    private static Logger log = LoggerFactory.getLogger(FileUploadController.class);


    @Autowired
    private LetterService letterService;

    @Autowired
    private ImageService imageService;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            log.debug(" no file to upload");
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        log.info("Started file uploading");
        imageService.updateImage(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }



    @GetMapping("/get")
    public ResponseEntity<byte[]> readImage() throws JsonProcessingException {

        Optional<Letter> image = letterService.getLetter(1);
        Letter letter = image.get();
        byte[] bytes = letter.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");
     //   ObjectMapper mapper = new ObjectMapper();
       // String jsonInString = mapper.writeValueAsString(bytes);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
