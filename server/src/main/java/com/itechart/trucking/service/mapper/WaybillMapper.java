package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Waybill;
import com.itechart.trucking.service.dto.WaybillDto;
import com.itechart.trucking.util.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Waybill} and its DTO called {@link WaybillDto}.
 *
 * @author blink7
 * @version 1.2
 * @since 2017-12-11
 */
@Mapper(uses = {WaybillCheckpointMapper.class})
public interface WaybillMapper {

    WaybillMapper INSTANCE = Mappers.getMapper(WaybillMapper.class);

    @Mapping(target = "departureDate", source = "departureDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(target = "issueDate", source = "issueDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(target = "invoiceId", source = "invoice.id")
    WaybillDto waybillToWaybillDto(Waybill waybill);
}