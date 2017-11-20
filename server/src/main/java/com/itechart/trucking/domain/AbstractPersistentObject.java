package com.itechart.trucking.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-11-18
 */
@MappedSuperclass
public abstract class AbstractPersistentObject implements PersistentObject, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistentObject that = (PersistentObject) o;

        return (id != null ? id.equals(that.getId()) : that.getId() == null)
                && (version != null ? version.equals(that.getVersion()) : that.getVersion() == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : super.hashCode();
        result = 31 * result + (version != null ? version.hashCode() : super.hashCode());
        return result;
    }
}