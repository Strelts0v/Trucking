package com.itechart.trucking.service.dto;

/**
 * A DTO representing a client.
 *
 * @author blink7
 * @version 1.1
 * @since 2017-11-23
 */
public class ClientDto extends AbstractDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}