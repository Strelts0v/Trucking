package com.itechart.trucking.controller;

import com.itechart.trucking.dao.WaybillDao;
import com.itechart.trucking.service.WaybillService;
import com.itechart.trucking.service.dto.WaybillDto;
import com.itechart.trucking.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.itechart.trucking.auth.SecurityConstants.*;
import static com.itechart.trucking.util.Constants.NUMBER_REGEX;

/**
 * REST controller for managing waybills.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-12-13
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
        final List<WaybillDto> waybills = waybillService.getAllWaybills(page, size);
        return ResponseEntity.ok(waybills);
    }

    @GetMapping("/waybills/size")
    public ResponseEntity<Integer> getTotalWaybillsSize() {
        final int size = waybillDao.size();
        log.debug("REST request to get total Waybills size: {}", size);
        return ResponseEntity.ok(size);
    }

    @GetMapping("/waybill/{id:" + NUMBER_REGEX + "}")
    public ResponseEntity<WaybillDto> getWaybill(@PathVariable int id) {
        log.debug("REST request to get a Waybill for id: {}", id);
        return ResponseUtil.wrapOrNotFound(waybillService.getWaybillById(id));
    }

    @PostMapping("/waybill/register")
    @Secured(AUTHORIZED_ROLE_MANAGER)
    public ResponseEntity<WaybillDto> registerWaybill(@Valid @RequestBody WaybillDto waybillDto) {
        log.debug("REST request to register a new Waybill : {}", waybillDto);
        return waybillService.registerWaybill(waybillDto)
                .map(registeredWaybill -> new ResponseEntity<>(registeredWaybill, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping("/waybill/check")
    @Secured(AUTHORIZED_ROLE_DRIVER)
    public ResponseEntity<WaybillDto> checkWaybill(@RequestBody WaybillDto waybillDto) {
        log.debug("REST request to check Waybill : {}", waybillDto);
        return waybillService.checkWaybill(waybillDto)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }
}
