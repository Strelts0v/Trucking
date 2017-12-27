package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Warehouse;
import com.itechart.trucking.service.dto.WarehouseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Warehouse} and its DTO called {@link WarehouseDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-22
 */
@Mapper
public interface WarehouseMapper {

    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    WarehouseDto warehouseToWarehouseDto(Warehouse warehouse);
}