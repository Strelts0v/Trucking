package com.itechart.trucking.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-11-18
 */
@Entity
@Table(name = "waybills_has_checkpoints")
public class WaybillCheckpoint implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "waybills_id")
    private Waybill waybill;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "checkpoints_id")
    private Checkpoint checkpoint;

    @Column(name = "checkpoint_datetime")
    private LocalDate checkDate;

    public WaybillCheckpoint() {
    }

    public WaybillCheckpoint(Waybill waybill, Checkpoint checkpoint) {
        this.waybill = waybill;
        this.checkpoint = checkpoint;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }
}