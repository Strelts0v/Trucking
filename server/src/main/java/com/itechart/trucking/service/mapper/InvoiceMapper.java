package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.util.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Invoice} and its DTO called {@link InvoiceDto}.
 *
 * @author blink7
 * @version 1.3
 * @since 2017-12-11
 */
@Mapper(uses = {ClientMapper.class, ItemMapper.class, UserMapper.class, WaybillMapper.class, LossActMapper.class})
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "issueDate", target = "issueDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(source = "checkDate", target = "checkDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(source = "lossActIssueDate", target = "lossActIssueDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(source = "itemConsignments", target = "consignments")
    InvoiceDto invoiceToInvoiceDto(Invoice invoice);
}