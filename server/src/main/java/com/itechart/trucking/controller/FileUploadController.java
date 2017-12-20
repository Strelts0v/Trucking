package com.itechart.trucking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.trucking.domain.Letter;
import com.itechart.trucking.service.LetterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
import java.util.Optional;

/**
 * author Vlad Sytyi
 * 18.12.2017
 */
@RestController
@RequestMapping("/api/image")
public class FileUploadController {

    private static final String FILE_PATH_DOWN = "picture/bday.png";
    private static final String FILE_NAME = "bbday.png";
    private final Path rootLocation = Paths.get("resources/picture/");

    private static Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private LetterService letterService;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            log.debug(" no file to upload");
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            log.info("Started file uploading");

           // byte[] bytes = file.getBytes();
            InputStream inputStream = file.getInputStream();
//            String path = applicationContext.getResource("/picture").toString();
//            System.out.println(path);
//            OutputStream os = new FileOutputStream(path);
//            IOUtils.copy(inputStream, os);


//
//            URL resourceUrl = getClass().getResource("/picture");
//            File fileqq = new File(resourceUrl.toURI());
//            OutputStream output = new FileOutputStream(fileqq);
//            IOUtils.copy(inputStream, output);

//            Files.copy(file.getInputStream(), this.rootLocation.resolve("bday.png") );

            //URL resourceUrl = getClass().getResource("/picture");

            //applicationContext.getResources();
           // Path www = Paths.get(path);
          //  Files.write(www,bytes);


      //      OutputStream os = new FileOutputStream(path + "/bday.png");
       //     FileUtils.write


            //OutputStream os = applicationContext.getResource("picture").ge;
//            FileOutputStream fileOuputStream = new FileOutputStream(path);
//            fileOuputStream.flush();
           // IOUtils.copy(inputStream, os);
          //  os.write(bytes);



          //  File file1 = new File(resourceUrl.toURI());



            // Get the file and save it somewhere


//            File parentDirectory = new File(new URI(resourceUrl.toString()));

            //        byte[] bytes = file.getBytes();

//            String dir = FileUploadController.class.getResource("/picture").getFile();
//            Path path = Paths.get(dir);
//            Files.write(path, bytes);


//            File files = new File(resourceUrl.toURI());
//            OutputStream output = new FileOutputStream(files);
//            output.


            //   OutputStream os = new FileOutputStream(dir + "/bday.png");

            //     FileUtils.writeByteArrayToFile(new File("dir"), bytes);
//            final PrintStream printStream = new PrintStream(os);
//            printStream.print(bytes);
//            printStream.close();

//            Resource resource = new ClassPathResource("/picture/bday.png");
//            File files = resource.getFile();

//            URL url = this.getClass().getResource("/pictures");
//            File parentDirectory = new File(new URI(url.toString()));
//            //new File(parentDirectory, "newProperties.properties");


            //   Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            log.debug("error while was reading file from resources", e);
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
//
//    @GetMapping("/get")
//    public ResponseEntity<String> readLetter() throws JsonProcessingException {
//
//        Optional<Letter> letter = letterService.getLetter(1);
//

//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH_DOWN);
//        byte[] imgBytes = new byte[0];
//        try {
//            imgBytes = IOUtils.toByteArray(inputStream);
//        } catch (IOException e) {
//            log.debug("error while was reading file from resources", e);
//        }
//        byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
//        String imgDataAsBase64 = new String(imgBytesAsBase64);
//        String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
//        log.info("readed image ");
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonInString = mapper.writeValueAsString(imgAsBase64);
//        return new ResponseEntity<>(jsonInString, HttpStatus.OK);
//    }
}
