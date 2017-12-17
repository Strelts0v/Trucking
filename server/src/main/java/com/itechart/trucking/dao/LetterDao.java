package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Letter;

import java.util.Optional;

/**
 * @author Vlad_Sytyi
 * @since 10.12.2017
 */
public interface LetterDao {


    /**
     * This method creates field text in Congragulation letter
     * @param letter
     */
    Letter createLetter(Letter letter);
    /**
     * This method updates field text in Congragulation letter
     * @param letter
     */
    void updateLetter(Letter letter);

    /**
     * This method reads field text in Congragulation letter
     * @param id
     */
    Object readLetter(Integer id);

    /**
     * This method deletes letter from dataBase
     * @param letter
     */
    void deleteLetter(Letter letter);


}
