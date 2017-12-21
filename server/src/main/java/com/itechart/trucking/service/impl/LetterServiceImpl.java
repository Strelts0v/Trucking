package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.LetterDao;
import com.itechart.trucking.domain.Letter;
import com.itechart.trucking.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Service class for managing items.
 *
 * @author Vlad Sytyi
 * @version 1.0
 * @since 2017-12-12
 */

@Service
@Transactional
public class LetterServiceImpl implements LetterService {


    private final LetterDao letterDao;

    @Autowired
    public LetterServiceImpl(LetterDao letterDao) {
        this.letterDao = letterDao;
    }


    @Override
    public void updateLetter(Letter letter) {
        letterDao.readLetter(1).ifPresent(letter1 -> {
            letter1.setText(letter.getText());
            letter1.setColor(letter.getColor());
        });
    }

    @Override
    public Optional<Letter> getLetter(int id) {



        return letterDao.readLetter(id);
    }
}
