package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.LetterDao;
import com.itechart.trucking.domain.Letter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public Letter createLetter(Letter letter) {
        em.persist(letter);
        return letter;
    }

    @Override
    public void updateLetter(Letter letter) {

        em.persist(letter);
    }

    @Override
    public Optional<Letter> readLetter(Integer id) {
        final String jpqlQuery =
                "select c from Letter c where c.id = :id";
        TypedQuery<Letter> query = em.createQuery(jpqlQuery, Letter.class);
        query.setParameter("id",id);
        Letter letter = query.getSingleResult();
        return Optional.ofNullable(letter);
    }

    @Override
    public void deleteLetter(Letter letter) {
            em.remove(letter);
    }
}
