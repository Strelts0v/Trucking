package com.itechart.trucking.service.dto;

public class WaybillCheckpointDto {

    private CheckpointDto checkpoint;
    private String checkDate;

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
}