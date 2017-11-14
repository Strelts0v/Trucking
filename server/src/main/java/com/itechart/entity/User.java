package com.itechart.entity;

import java.util.Date;


public class User {

    private String firtstname;
    private String lastname;
    private String middlename;
    private Date birthday;
    private String email;
    private String city;
    private String street;
    private String house;
    private int apartment;
    private Enum role;
    private String login;
    private String password;
    private String passport;

    public User() {
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

    public Enum getRole() {
        return role;
    }

    public void setRole(Enum role) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (apartment != user.apartment) return false;
        if (firtstname != null ? !firtstname.equals(user.firtstname) : user.firtstname != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        if (middlename != null ? !middlename.equals(user.middlename) : user.middlename != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (street != null ? !street.equals(user.street) : user.street != null) return false;
        if (house != null ? !house.equals(user.house) : user.house != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return passport != null ? passport.equals(user.passport) : user.passport == null;
    }

    @Override
    public int hashCode() {
        int result = firtstname != null ? firtstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + apartment;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firtstname='" + firtstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", apartment=" + apartment +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
