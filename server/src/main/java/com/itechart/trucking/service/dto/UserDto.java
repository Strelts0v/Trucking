package com.itechart.trucking.service.dto;

import com.itechart.trucking.domain.User;

import java.util.Set;

/**
 * A DTO representing a user.
 *
 * @author blink7
 * @version 1.2
 * @since 2017-12-11
 */
public class UserDto extends AbstractDto {

    private String firstName;
    private String lastName;
    private String middleName;
    private String birthday;
    private String email;
    private String city;
    private String street;
    private String house;
    private Integer apartment;
    private Set<User.Role> roles;
    private String login;
    private String password;
    private String passport;
    private Boolean busy;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firtstname) {
        this.firstName = firtstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middlename) {
        this.middleName = middlename;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public Set<User.Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<User.Role> roles) {
        this.roles = roles;
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

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy) {
        this.busy = busy;
    }
}