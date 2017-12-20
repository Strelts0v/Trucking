package com.itechart.trucking.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;

/**
 * @author Vlad_Sytyi
 * @since 10.12.2017
 */
@Entity
@Table(name="letter")
public class Letter extends AbstractPersistentObject{

    @Column(name="text")
    private String text;

    @Column(name="color")
    private String color;

    @Column(name="image")
    private byte[] image;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
