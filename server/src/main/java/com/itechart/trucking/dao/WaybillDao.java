package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Waybill;

import java.util.List;
import java.util.Optional;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-11-18
 */
public interface WaybillDao {

    /**
     * Returns all instances of the {@link Waybill}.
     *
     * @return all waybills
     */
    List<Waybill> findAll();

    /**
     * Returns all instances of the {@link Waybill} by the given page number and size.
     *
     * @param pageNumber
     * @param pageSize
     * @return all waybills per page.
     */
    List<Waybill> findAllByPage(int pageNumber, int pageSize);

    /**
     * Retrieves a waybill by its id.
     *
     * @param id
     * @return the waybill with the given id or {@literal null} if none found.
     */
    Optional<Waybill> findOne(Integer id);

    /**
     * Saves a given waybill.
     *
     * @param waybill to save
     */
    void save(Waybill waybill);

    /**
     * Deletes a given waybill.
     *
     * @param waybill to delete
     */
    void delete(Waybill waybill);

    /**
     * @return the number of waybills in the DB
     */
    int size();
}
