package com.itechart.trucking.controller;

import com.itechart.trucking.domain.Letter;
import com.itechart.trucking.service.LetterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * author: Vlad Sytyi
 * 20.12.2017
 */
@RestController
@RequestMapping("/api/letter")
public class LetterController {

    private final LetterService letterService;
    private static Logger log = LoggerFactory.getLogger(LetterController.class);

    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }


    @PostMapping("/update")
    public ResponseEntity<Void> updateLetter (@RequestBody Letter letter){
        letterService.updateLetter(letter);
        log.info("updated letter: " + letter);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @RequestMapping("/read")
    public ResponseEntity<Letter> readLetter(){

        Optional<Letter> letter = letterService.getLetter(1);
        log.info("readed letter with id: " + 1);
        return new ResponseEntity<>(letter.map(letter1 -> {
            Letter letterDto = new Letter();
            letterDto.setText(letter1.getText());
            letterDto.setColor(letter1.getColor());
            return letterDto;
        }).orElse(new Letter()), HttpStatus.OK);
    }


}
