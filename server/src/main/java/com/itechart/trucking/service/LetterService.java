package com.itechart.trucking.service;

import com.itechart.trucking.domain.Letter;

import java.util.Optional;

public interface LetterService {

    /**
     * this method is used to update text anf coloe field
     * @param letter
     */
    void updateLetter(Letter letter);

    /**
     * This method is used to achieve data from database
     * @param id
     * @return
     */
    Optional<Letter> getLetter(int id);

}
