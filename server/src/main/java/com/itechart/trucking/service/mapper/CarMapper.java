package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Car;
import com.itechart.trucking.service.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Car} and its DTO called {@link CarDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-22
 */
@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto carToCarDto(Car car);
}