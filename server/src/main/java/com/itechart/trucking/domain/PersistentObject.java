package com.itechart.trucking.domain;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-11-18
 */
public interface PersistentObject {

    Integer getId();
    void setId(Integer id);

    Integer getVersion();
    void setVersion(Integer version);
}