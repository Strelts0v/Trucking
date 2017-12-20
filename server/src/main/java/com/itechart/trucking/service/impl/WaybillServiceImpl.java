package com.itechart.trucking.service.impl;

import com.itechart.trucking.dao.*;
import com.itechart.trucking.domain.Checkpoint;
import com.itechart.trucking.domain.Waybill;
import com.itechart.trucking.service.WaybillService;
import com.itechart.trucking.service.dto.WaybillDto;
import com.itechart.trucking.service.dto.WaybillSearchCriteriaDto;
import com.itechart.trucking.service.mapper.WaybillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.itechart.trucking.util.Utils.*;

/**
 * Service class for managing waybills.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-12-13
 */
@Service
@Transactional
public class WaybillServiceImpl implements WaybillService {

    private final Logger log = LoggerFactory.getLogger(WaybillServiceImpl.class);

    private final WaybillDao waybillDao;
    private final InvoiceDao invoiceDao;
    private final UserDao userDao;
    private final CarDao carDao;
    private final WarehouseDao warehouseDao;
    private final CheckpointDao checkpointDao;

    private final WaybillMapper waybillMapper;

    @Autowired
    public WaybillServiceImpl(WaybillDao waybillDao, InvoiceDao invoiceDao, UserDao userDao, CarDao carDao,
                              WarehouseDao warehouseDao, CheckpointDao checkpointDao, WaybillMapper waybillMapper) {

        this.waybillDao = waybillDao;
        this.invoiceDao = invoiceDao;
        this.userDao = userDao;
        this.carDao = carDao;
        this.warehouseDao = warehouseDao;
        this.checkpointDao = checkpointDao;
        this.waybillMapper = waybillMapper;
    }

    @Override
    public Optional<WaybillDto> registerWaybill(final WaybillDto waybillDto) {
        return invoiceDao.findOne(waybillDto.getInvoiceId()).map(invoice -> {
            Waybill newWaybill = new Waybill();
            invoice.setWaybill(newWaybill);
            newWaybill.setInvoice(invoice);
            newWaybill.setDepartureDate(LocalDate.now());
            newWaybill.setIssueDate(LocalDate.now());
            newWaybill.setStatus(Waybill.Status.STARTED);
            newWaybill.setDistance(waybillDto.getDistance());

            userDao.getUserById(waybillDto.getDriver().getId()).ifPresent(driver -> {
                driver.setBusy(true);
                newWaybill.setDriver(driver);
            });

            carDao.findOne(waybillDto.getCar().getId()).ifPresent(newWaybill::setCar);
            warehouseDao.getWarehouseById(waybillDto.getFrom().getId()).ifPresent(newWaybill::setFrom);
            warehouseDao.getWarehouseById(waybillDto.getTo().getId()).ifPresent(newWaybill::setTo);

            waybillDto.getWaybillCheckpoints().forEach(waybillCheckpointDto -> {
                Checkpoint checkpoint = checkpointDao.findOneByName(waybillCheckpointDto.getCheckpoint().getName())
                        .orElseGet(() -> checkpointDao.save(new Checkpoint(
                                waybillCheckpointDto.getCheckpoint().getName(),
                                waybillCheckpointDto.getCheckpoint().getAdditionName(),
                                waybillCheckpointDto.getCheckpoint().getPlaceId(),
                                waybillCheckpointDto.getCheckpoint().getLat(),
                                waybillCheckpointDto.getCheckpoint().getLng())));
                newWaybill.addCheckpoint(checkpoint);
            });

            waybillDao.save(newWaybill);
            log.debug("Registered a new Waybill: {}", newWaybill);

            return newWaybill;
        }).map(waybillMapper::waybillToWaybillDto);
    }

    @Override
    public Optional<WaybillDto> checkWaybill(final WaybillDto waybillDto) {
        return waybillDao.findOne(waybillDto.getId()).map(waybill -> {
            waybill.getWaybillCheckpoints().forEach(waybillCheckpoint ->
                    waybillDto.getWaybillCheckpoints()
                            .stream()
                            .filter(wc ->
                                    Objects.equals(
                                            wc.getCheckpoint().getId(),
                                            waybillCheckpoint.getCheckpoint().getId()))
                            .filter(waybillCheckpointDto ->
                                    waybillCheckpointDto.isChecked() != null && waybillCheckpointDto.isChecked())
                            .findFirst()
                            .ifPresent(waybillCheckpointDto -> {
                                if (waybillCheckpoint.isChecked() == null || !waybillCheckpoint.isChecked()) {
                                    waybillCheckpoint.setChecked(true);
                                    waybillCheckpoint.setCheckDate(LocalDate.now());
                                }
                            }));
            return waybill;
        }).map(waybillMapper::waybillToWaybillDto);
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

    @Override
    public List<WaybillDto> getInvoicesBySearch(WaybillSearchCriteriaDto criteria, int pageNumber, int pageSize) {
        return waybillDao
                .searchWaybills(
                        criteria.getFrom(),
                        criteria.getTo(),
                        criteria.getInvoiceNumber(),
                        jsonToLocalDate(criteria.getIssueDate()),
                        pageNumber, pageSize)
                .stream()
                .map(waybillMapper::waybillToWaybillDtoForList)
                .collect(Collectors.toList());
    }

    @Override
    public int getSearchSize(WaybillSearchCriteriaDto criteria) {
        return waybillDao
                .searchSize(
                        criteria.getFrom(),
                        criteria.getTo(),
                        criteria.getInvoiceNumber(),
                        jsonToLocalDate(criteria.getIssueDate()));
    }
}