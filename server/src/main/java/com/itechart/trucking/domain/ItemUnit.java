package com.itechart.trucking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-12-11
 */
@Entity
@Table(name = "item_unit_codes")
public class ItemUnit extends AbstractPersistentObject {

    @Column(name = "unit_code")
    private String unitCode;

    public ItemUnit() {
    }

    public ItemUnit(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
