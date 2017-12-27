package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.LossAct;
import com.itechart.trucking.service.dto.LossActDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link LossAct} and its DTO called {@link LossActDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-22
 */
@Mapper
public interface LossActMapper {

    LossActMapper INSTANCE = Mappers.getMapper(LossActMapper.class);

    LossActDto lossActToLossActDto(LossAct lossAct);
}