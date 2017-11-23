package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.CheckpointDao;
import com.itechart.trucking.domain.Checkpoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class for {@link JpaCheckpointDao}
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("dev_blink7")
public class JpaCheckpointDaoTest {

    @Autowired
    private EntityManager em;

    private CheckpointDao checkpointDao;

    private Checkpoint checkpoint;

    private static final String NAME = "City";
    private static final String LAT = "37.4224764";
    private static final String LNG = "-122.0842499";

    @Before
    public void setUp() {
        checkpointDao = new JpaCheckpointDao(em);
        checkpoint = new Checkpoint(NAME, LAT, LNG);
    }

    @Test
    public void findOneByNameShouldReturnCorrespondObject() throws Exception {
        checkpointDao.save(checkpoint);

        Optional<Checkpoint> result = checkpointDao.findOneByName(NAME);
        assertTrue("Expected an object", result.isPresent());
        assertEquals("Expected equals objects", checkpoint, result.orElse(new Checkpoint()));
    }

    @Test
    public void findOneByLocationShouldReturnCorrespondObject() throws Exception {
        checkpointDao.save(checkpoint);

        Optional<Checkpoint> result = checkpointDao.findOneByLocation(LAT, LNG);
        assertTrue("Expected an object", result.isPresent());
        assertEquals("Expected equals objects", checkpoint, result.orElse(new Checkpoint()));
    }

    @Test
    public void findOneNonExistentNameShouldReturnNoObject() throws Exception {
        Optional<Checkpoint> result = checkpointDao.findOneByName(NAME);

        assertFalse("No object expected", result.isPresent());
    }

    @Test
    public void findOneNonExistentLocationShouldReturnNoObject() throws Exception {
        Optional<Checkpoint> result = checkpointDao.findOneByLocation(LAT, LNG);

        assertFalse("No object expected", result.isPresent());
    }

    @Test
    public void afterSaveCheckpointsFindAllShouldReturnExactCount() throws Exception {
        checkpointDao.save(checkpoint);

        int size = 3;
        for (int i = 1; i < size; i++) {
            Checkpoint checkpoint = new Checkpoint();
            checkpoint.setName(NAME + i);
            checkpointDao.save(checkpoint);
        }

        List<Checkpoint> checkpoints = checkpointDao.findAll();
        assertSame("Expected the same size of the result list", size, checkpoints.size());
    }

    @Test
    public void deleteTest() throws Exception {
        checkpointDao.save(checkpoint);
        checkpointDao.delete(checkpoint);

        Optional<Checkpoint> result = checkpointDao.findOneByName(NAME);
        assertFalse("No object expected", result.isPresent());
    }
}