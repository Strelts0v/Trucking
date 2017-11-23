package com.itechart.trucking.service.mapper;

import com.itechart.trucking.Application;
import com.itechart.trucking.domain.Checkpoint;
import com.itechart.trucking.service.dto.CheckpointDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Test class for {@link InvoiceMapper}
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("dev_blink7")
public class CheckpointMapperTest {

    @Autowired
    private CheckpointMapper checkpointMapper;

    private static final String NAME = "City";
    private static final String LAT = "37.4224764";
    private static final String LNG = "-122.0842499";
    private static final Integer ID = 666;

    @Test
    public void shouldMapCheckpointToCheckpointDto() {
        Checkpoint checkpoint = new Checkpoint(NAME, LAT, LNG);
        checkpoint.setId(ID);

        CheckpointDto checkpointDto = checkpointMapper.checkpointToCheckpointDto(checkpoint);

        assertNotNull("Expected the converted checkpoint", checkpointDto);
        assertEquals("Expected the same name field", NAME, checkpointDto.getName());
        assertEquals("Expected the same latitude value", LAT, checkpointDto.getLat());
        assertEquals("Expected the same longitude value", LNG, checkpointDto.getLng());
        assertEquals("Expected the same id", ID, checkpointDto.getId());
    }
}