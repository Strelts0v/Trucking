package com.itechart.trucking.controller;

import com.itechart.trucking.domain.Letter;
import com.itechart.trucking.service.LetterService;
import com.itechart.trucking.service.impl.LetterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/letter")
public class LetterController {

    private final LetterService letterService;
    private static Logger log = LoggerFactory.getLogger(LetterController.class);

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }


    @RequestMapping("/update")
    public ResponseEntity<Void> updateLetter (@RequestParam(value="letter") Letter letter){
        letterService.updateLetter(letter);
        log.info("updated letter: " + letter);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @RequestMapping("/read")
    public ResponseEntity<Letter> readLetter(){

        Optional<Letter> letter = letterService.getLetter(1);
       // Letter json = letter.get();
        log.info("readed letter with id: " + 1);
        return new ResponseEntity<>(letter.orElse(new Letter()), HttpStatus.OK);
    }


}
