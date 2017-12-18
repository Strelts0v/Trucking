package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.InvoiceResult;
import com.itechart.trucking.service.dto.InvoiceResultDto;
import com.itechart.trucking.util.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link InvoiceResult} and its DTO called {@link InvoiceResultDto}.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-12-16
 */
@Mapper(uses = UserMapper.class)
public interface InvoiceResultMapper {

    InvoiceResultMapper INSTANCE = Mappers.getMapper(InvoiceResultMapper.class);

    @Mapping(source = "completeDate", target = "completeDate", dateFormat = Constants.DATE_FORMAT)
    InvoiceResultDto invoiceResultToInvoiceResultDto(InvoiceResult invoice);
}
