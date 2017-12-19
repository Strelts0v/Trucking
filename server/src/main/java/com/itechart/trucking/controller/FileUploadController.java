package com.itechart.trucking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * author Vlad Sytyi
 * 18.12.2017
 */
@RestController
@RequestMapping("/api/image")
public class FileUploadController  {

    private static final String FILE_PATH_DOWN = "picture/bday.png";
    private static final String FILE_PATH_UP= "picture";
    private static Logger log = LoggerFactory.getLogger(FileUploadController.class);


    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file,
                                                 RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            log.debug(" no file to upload");
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            log.debug("Started file uploading");
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILE_PATH_UP + "bday.png");
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            log.debug("error while was reading file from resources" , e);
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/get")
    public ResponseEntity<String> readLetter() throws JsonProcessingException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH_DOWN);
        byte[] imgBytes = new byte[0];
        try {
            imgBytes = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.debug("error while was reading file from resources" , e);
        }
        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
        String imgDataAsBase64 = new String(imgBytesAsBase64);
        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
        log.info("readed image ");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(imgAsBase64);
        return new ResponseEntity<>(jsonInString, HttpStatus.OK);
    }
}
