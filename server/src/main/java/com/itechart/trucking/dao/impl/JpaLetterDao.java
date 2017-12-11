package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.LetterDao;
import com.itechart.trucking.domain.Letter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @author Vlad_Sytyi
 * @since 10.12.2017
 */
@Repository
public class JpaLetterDao implements LetterDao {

    @Autowired
    private EntityManager em;

    @Override
    public void createLetter(Letter letter) {
        em.persist(letter);
    }

    @Override
    public void updateLetter(Letter letter) {
        em.persist(letter);
    }

    @Override
    public Optional<Letter> readLetter(Integer id) {
        return Optional.ofNullable(em.find(Letter.class, id));
    }

    @Override
    public void deleteLetter(Letter letter) {

    }
}
