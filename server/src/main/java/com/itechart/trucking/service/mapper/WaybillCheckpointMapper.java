package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.WaybillCheckpoint;
import com.itechart.trucking.service.dto.WaybillCheckpointDto;
import com.itechart.trucking.util.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link WaybillCheckpoint} and its DTO called {@link WaybillCheckpointDto}.
 *
 * @author blink7
 * @version 1.2
 * @since 2017-12-11
 */
@Mapper(uses = {UserMapper.class, CarMapper.class, WarehouseMapper.class})
public interface WaybillCheckpointMapper {

    WaybillCheckpointMapper INSTANCE = Mappers.getMapper(WaybillCheckpointMapper.class);

    @Mapping(target = "checkDate", source = "checkDate", dateFormat = Constants.DATE_FORMAT)
    WaybillCheckpointDto waybillCheckpointToWaybillCheckpointDto(WaybillCheckpoint waybillCheckpoint);
}