package com.itechart.trucking.service.mapper;

import com.itechart.trucking.Application;
import com.itechart.trucking.domain.Checkpoint;
import com.itechart.trucking.service.dto.CheckpointDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Test class for {@link InvoiceMapper}
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("dev_blink7")
public class CheckpointMapperTest {

    private static final String NAME = "City";
    private static final String LAT = "37.4224764";
    private static final String LNG = "-122.0842499";

    @Test
    public void shouldMapCheckpointToCheckpointDto() {
        Checkpoint checkpoint = new Checkpoint(NAME, LAT, LNG);

        CheckpointDto checkpointDto = CheckpointMapper.INSTANCE.checkpointToCheckpointDto(checkpoint);

        assertNotNull(checkpointDto);
        assertEquals(NAME, checkpointDto.getName());
        assertEquals(LAT, checkpointDto.getLat());
        assertEquals(LNG, checkpointDto.getLng());
    }
}