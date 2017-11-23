package com.itechart.trucking.dao;

import com.itechart.trucking.domain.Checkpoint;

import java.util.List;
import java.util.Optional;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-11-20
 */
public interface CheckpointDao {

    /**
     * Returns all instances of the {@link Checkpoint}.
     *
     * @return all checkpoints
     */
    List<Checkpoint> findAll();

    /**
     * Retrieves a checkpoint by its name.
     *
     * @param name of the checkpoint
     * @return the checkpoint or {@literal null} if none found.
     */
    Optional<Checkpoint> findOneByName(String name);

    /**
     * Retrieves a checkpoint by its location.
     *
     * @param lat the geocoded latitude value
     * @param lng the geocoded longitude value
     * @return the checkpoint or {@literal null} if none found.
     */
    Optional<Checkpoint> findOneByLocation(String lat, String lng);

    /**
     * Saves a given checkpoint.
     *
     * @param checkpoint to save
     */
    void save(Checkpoint checkpoint);

    /**
     * Deletes a given checkpoint.
     *
     * @param checkpoint to delete
     */
    void delete(Checkpoint checkpoint);
}