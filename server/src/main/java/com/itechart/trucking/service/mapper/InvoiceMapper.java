package com.itechart.trucking.service.mapper;

import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.service.dto.InvoiceDto;
import com.itechart.trucking.service.mapper.qualifier.WaybillQualifier;
import com.itechart.trucking.service.mapper.qualifier.DefaultWaybill;
import com.itechart.trucking.util.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for the entity {@link Invoice} and its DTO called {@link InvoiceDto}.
 *
 * @author blink7
 * @version 1.4
 * @since 2017-12-12
 */
@Mapper(uses = {ClientMapper.class, ItemMapper.class, UserMapper.class, WaybillMapper.class, LossActMapper.class})
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "issueDate", target = "issueDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(source = "checkDate", target = "checkDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(source = "lossActIssueDate", target = "lossActIssueDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(source = "itemConsignments", target = "consignments")
    @Mapping(target = "waybill", qualifiedBy = {WaybillQualifier.class, DefaultWaybill.class})
    InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    @Mapping(source = "issueDate", target = "issueDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(source = "checkDate", target = "checkDate", dateFormat = Constants.DATE_FORMAT)
    @Mapping(target = "waybill", ignore = true)
    @Mapping(target = "lossActs", ignore = true)
    @Mapping(target = "lossActIssueDate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "consignments", ignore = true)
    InvoiceDto invoiceToInvoiceDtoForList(Invoice invoice);
}