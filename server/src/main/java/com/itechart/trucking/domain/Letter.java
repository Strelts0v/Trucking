package com.itechart.trucking.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Vlad_Sytyi
 * @since 10.12.2017
 */
@Entity
@Table(name="congragulation_letter")
public class Letter extends AbstractPersistentObject{

    @Column(name="text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
