package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.LetterDao;
import com.itechart.trucking.domain.Letter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;

/**
 * @author Vlad_Sytyi
 * @since 10.12.2017
 */
@Transactional
@Repository
public class JpaLetterDao implements LetterDao {



    @PersistenceContext
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
    public Object readLetter(Integer id) {
//        final String jpqlQuery =
//                "select c from congragulation_letter c where c.id = :id";
//        TypedQuery<Letter> query = em.createQuery(jpqlQuery, Letter.class);
//        query.setParameter("id",id);

         String sqlQuery = "" +
                "SELECT text , color From congragulation_letter WHERE id =1";

        Query query = em.createQuery(sqlQuery);

       // Letter letter = query.getSingleResult();
        return query.getSingleResult();
    }

    @Override
    public void deleteLetter(Letter letter) {
            em.remove(letter);
    }
}
