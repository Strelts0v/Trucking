package com.itechart.trucking.controller;

import com.itechart.trucking.dao.WaybillDao;
import com.itechart.trucking.service.WaybillService;
import com.itechart.trucking.service.dto.WaybillDto;
import com.itechart.trucking.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.itechart.trucking.util.Constants.NUMBER_REGEX;

/**
 * REST controller for managing waybills.
 *
 * @author blink7
 * @version 1.0
 * @since 2017-12-12
 */
@RestController
@RequestMapping("/api")
public class WaybillController {

    private final Logger log = LoggerFactory.getLogger(WaybillController.class);

    private final WaybillService waybillService;

    private final WaybillDao waybillDao;

    public WaybillController(WaybillService waybillService, WaybillDao waybillDao) {
        this.waybillService = waybillService;
        this.waybillDao = waybillDao;
    }

    @GetMapping("/waybills/{page:" + NUMBER_REGEX + "}/{size:" + NUMBER_REGEX + "}")
    public ResponseEntity<List<WaybillDto>> getAllWaybills(@PathVariable int page, @PathVariable int size) {
        log.debug("REST request to get all Waybills for page: {} and size: {}", page, size);
        final List<WaybillDto> invoices = waybillService.getAllWaybills(page, size);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/waybills/size")
    public ResponseEntity<Integer> getTotalWaybillsSize() {
        final int size = waybillDao.size();
        log.debug("REST request to get total Waybills size: {}", size);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    @GetMapping("/waybill/{id:" + NUMBER_REGEX + "}")
    public ResponseEntity<WaybillDto> getWaybill(@PathVariable int id) {
        log.debug("REST request to get a Waybill for id: {}", id);
        return ResponseUtil.wrapOrNotFound(waybillService.getWaybillById(id));
    }
}
