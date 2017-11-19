package com.itechart.trucking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gleb Streltsov
 * @version 1.1
 * @since 2017-11-15
 */
@Entity
@Table(name = "clients")
public class Client extends AbstractPersistentObject {

    @Column(name = "client_name")
    private String name;

    public Client(){
    }

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
