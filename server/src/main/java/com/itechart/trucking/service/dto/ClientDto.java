package com.itechart.trucking.service.dto;

/**
 * A DTO representing a client.
 *
 * @author blink7
 * @version 1.2
 * @since 2017-12-13
 */
public class ClientDto extends AbstractDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                '}';
    }
}