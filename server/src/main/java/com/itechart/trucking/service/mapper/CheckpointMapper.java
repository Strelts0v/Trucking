package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Checkpoint;
import com.itechart.trucking.service.dto.CheckpointDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Checkpoint} and its DTO called {@link CheckpointDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-22
 */
@Mapper
public interface CheckpointMapper {

    CheckpointMapper INSTANCE = Mappers.getMapper(CheckpointMapper.class);

    CheckpointDto checkpointToCheckpointDto(Checkpoint checkpoint);
}