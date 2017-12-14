package com.itechart.trucking.service;

import com.itechart.trucking.service.dto.WaybillDto;

import java.util.List;
import java.util.Optional;

public interface WaybillService {

    List<WaybillDto> getAllWaybills(int pageNumber, int pageSize);
    Optional<WaybillDto> getWaybillById(Integer id);
    Optional<WaybillDto> registerWaybill(WaybillDto waybillDto);
    Optional<WaybillDto> checkWaybill(WaybillDto waybillDto);
}
