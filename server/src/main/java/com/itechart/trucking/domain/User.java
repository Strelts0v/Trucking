package com.itechart.trucking.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iduser")
    private int id;

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
    private UserRole role;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_passport")
    private String passport;

    public enum UserRole {
        SYSADMIN,
        ADMIN,
        MANAGER,
        DISPATCHER,
        DRIVER,
        OWNER
    }

    public User() {
    }

    public User(String firtstname, String lastname, String middlename, Date birthday, String email, String city, String street, String house, int apartment, UserRole role, String login, String password, String passport) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
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

        if (id != user.id) return false;
        if (apartment != user.apartment) return false;
        if (firtstname != null ? !firtstname.equals(user.firtstname) : user.firtstname != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        if (middlename != null ? !middlename.equals(user.middlename) : user.middlename != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (street != null ? !street.equals(user.street) : user.street != null) return false;
        if (house != null ? !house.equals(user.house) : user.house != null) return false;
        if (role != user.role) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return passport != null ? passport.equals(user.passport) : user.passport == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firtstname != null ? firtstname.hashCode() : 0);
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
                "id=" + id +
                ", firtstname='" + firtstname + '\'' +
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

    @Convert
    private static class RoleConverter implements AttributeConverter<UserRole, String> {

        @Override
        public  String convertToDatabaseColumn(UserRole attribute) {
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
        public UserRole convertToEntityAttribute(String dbData) {
            switch (dbData) {
                case "A":
                    return UserRole.ADMIN;
                case "D":
                    return UserRole.DISPATCHER;
                case "DR":
                    return UserRole.DRIVER;
                case "O":
                    return UserRole.OWNER;
                case "M":
                    return UserRole.MANAGER;
                case "S":
                    return UserRole.SYSADMIN;
                default:
                    throw new IllegalArgumentException("Unknown " + dbData);
            }
        }
    }


}
