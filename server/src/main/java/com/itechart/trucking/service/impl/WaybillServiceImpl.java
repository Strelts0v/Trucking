package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.ClientDao;
import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.dao.WaybillDao;
import com.itechart.trucking.domain.Waybill;
import com.itechart.trucking.service.WaybillService;
import com.itechart.trucking.service.dto.WaybillDto;
import com.itechart.trucking.service.mapper.WaybillMapper;
import com.itechart.trucking.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing waybills.
 *
 * @author blink7
 * @version 0.3
 * @since 2017-12-12
 */
@Service
@Transactional
public class WaybillServiceImpl implements WaybillService {

    private final Logger log = LoggerFactory.getLogger(WaybillServiceImpl.class);

    private final WaybillDao waybillDao;
    private final InvoiceDao invoiceDao;
    private final ClientDao clientDao;

    private final WaybillMapper waybillMapper;

    @Autowired
    public WaybillServiceImpl(WaybillDao waybillDao, InvoiceDao invoiceDao, ClientDao clientDao,
                              WaybillMapper waybillMapper) {

        this.waybillDao = waybillDao;
        this.invoiceDao = invoiceDao;
        this.clientDao = clientDao;
        this.waybillMapper = waybillMapper;
    }

    public Optional<WaybillDto> registerWaybill(WaybillDto waybillDto) {
        Waybill waybill = new Waybill();
        waybill.setDepartureDate(LocalDate.parse(
                waybillDto.getDepartureDate(),
                DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)));

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WaybillDto> getAllWaybills(int pageNumber, int pageSize) {
        return waybillDao.findAllByPage(pageNumber, pageSize).stream()
                .map(waybillMapper::waybillToWaybillDtoForList)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WaybillDto> getWaybillById(Integer id) {
        return waybillDao.findOne(id).map(waybillMapper::waybillToWaybillDto);
    }
}