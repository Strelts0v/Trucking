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
        letter.setId(1);
        letter.setVersion(1);
        letter.setText("Уважаемый <fullname>!  " +
                " Поздравляем Вас с <age>-и летием." +
                " Желаем... (далее идет текст поздравления) " +
                " С уважением, коллектив ООО \"Транспортные системы\" ");
        System.out.println(letter.getText());


    }


    @Test
    public void getLetterById() throws Exception{

        Optional<Letter> letter1 = letterDao.readLetter(1);

        final String errorMessage = "Mesaages are not equals";
        assertEquals(errorMessage,letter,letter1.orElse(new Letter()));

    }

    @Test
    public void insertNewLetter() throws  Exception{

        Letter newLetter = new Letter();
        newLetter.setText("New text for sending");
        letterDao.createLetter(newLetter);

        final String errorMessage = "Inserted and expected letters are the same";
        Optional<Letter> letter1 = letterDao.readLetter(3);
        assertEquals(errorMessage,newLetter,letter1.orElse(new Letter()));

    }

}
