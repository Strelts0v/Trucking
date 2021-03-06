package com.itechart.trucking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itechart.trucking.domain.Letter;
import com.itechart.trucking.service.ImageService;
import com.itechart.trucking.service.LetterService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * author Vlad Sytyi
 * 18.12.2017
 */
@RestController
@RequestMapping("/api/image")
public class FileUploadController {

    private final static String FILE_PATH = "picture/bday.png";
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
        log.info("Sended image to another source");
        Optional<Letter> image = letterService.getLetter(1);
        Letter letter = image.get();
        HttpHeaders headers = new HttpHeaders();
        byte[] bytes = null;
        if (letter.getImage() == null) {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
            try {
                bytes = IOUtils.toByteArray(inputStream);
                headers.set("Content-Type", "image/png");
            } catch (IOException e) {
                log.debug("Exception while was reading image from file system", e);
            }
        } else {
            bytes = letter.getImage();
            headers.set("Content-Type", "image/png");
        }
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
