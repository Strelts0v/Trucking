package com.itechart.trucking.domain;

import java.util.UUID;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-11-18
 */
public interface PersistentObject {

    UUID getId();
    void setId(UUID id);

    Integer getVersion();
    void setVersion(Integer version);
}