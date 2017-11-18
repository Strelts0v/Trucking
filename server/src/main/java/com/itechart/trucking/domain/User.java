package com.itechart.trucking.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User extends AbstractPersistentObject {

    @Column(name = "user_firstname")
    private String firtstname;

    @Column(name = "user_lastname")
    private String lastname;

    @Column(name = "user_middlename")
    private String middlename;

    @Column(name = "user_birthday")
    private Date birthday;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_city")
    private String city;

    @Column(name = "user_street")
    private String street;

    @Column(name = "user_house")
    private String house;

    @Column(name = "user_apartment")
    private int apartment;

    @Column(name = "user_role")
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_passport")
    private String passport;

    public enum Role {
        SYSADMIN,
        ADMIN,
        MANAGER,
        DISPATCHER,
        DRIVER,
        OWNER
    }

    public User() {
    }

    public User(String firtstname, String lastname, String middlename, Date birthday, String email, String city, String street, String house, int apartment, Role role, String login, String password, String passport) {
        this.firtstname = firtstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthday = birthday;
        this.email = email;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.role = role;
        this.login = login;
        this.password = password;
        this.passport = passport;
    }

    public String getFirtstname() {
        return firtstname;
    }

    public void setFirtstname(String firtstname) {
        this.firtstname = firtstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Convert
    public static class RoleConverter implements AttributeConverter<Role, String> {

        @Override
        public String convertToDatabaseColumn(Role attribute) {
            switch (attribute) {
                case ADMIN:
                    return "A";
                case DISPATCHER:
                    return "D";
                case OWNER:
                    return "O";
                case DRIVER:
                    return "DR";
                case MANAGER:
                    return "M";
                case SYSADMIN:
                    return "S";
                default:
                    throw new IllegalArgumentException("Unknown " + attribute);
            }
        }

        @Override
        public Role convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "A":
                    return Role.ADMIN;
                case "D":
                    return Role.DISPATCHER;
                case "DR":
                    return Role.DRIVER;
                case "O":
                    return Role.OWNER;
                case "M":
                    return Role.MANAGER;
                case "S":
                    return Role.SYSADMIN;
                default:
                    throw new IllegalArgumentException("Unknown " + dbData);
            }
        }
    }
}