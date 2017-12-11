package com.itechart.trucking.service.dto;

/**
 * @author blink7
 * @version 1.1
 * @since 2017-12-11
 */
public class WaybillCheckpointDto {

    private CheckpointDto checkpoint;
    private String checkDate;
    private Boolean checked;

    public CheckpointDto getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(CheckpointDto checkpoint) {
        this.checkpoint = checkpoint;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}