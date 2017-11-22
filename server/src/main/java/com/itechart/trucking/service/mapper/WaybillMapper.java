package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Waybill;
import com.itechart.trucking.service.dto.WaybillDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity {@link Waybill} and its DTO called {@link WaybillDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-11-21
 */
@Mapper
public interface WaybillMapper {

    WaybillDto waybillToWaybillDto(Waybill waybill);

    List<WaybillDto> waybillsToWaybillDtos(List<Waybill> waybills);
}