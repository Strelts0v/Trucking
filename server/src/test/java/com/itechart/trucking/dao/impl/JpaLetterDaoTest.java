package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.LetterDao;
import com.itechart.trucking.domain.Letter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import java.util.Optional;

/**
 * Test class for {@link JpaLetterDao}
 *
 * @author Vlad Sytyi
 * @since 10.12.2017
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class JpaLetterDaoTest {




    @Autowired
    private LetterDao letterDao;

    private Letter letter;

    @Before
    public void setUp() {

        letter = new Letter();
        //letter.setId(1);
        //letter.setVersion(1);
        letter.setText("Test letter");
        letter.setColor("Test color");

        System.out.println(letter.toString());


    }
    @Test
    public void ReadLetterShouldReturnCorrespondObject() throws  Exception{



    }

    @Test
    public void getLetterById() throws Exception{



    }



}
