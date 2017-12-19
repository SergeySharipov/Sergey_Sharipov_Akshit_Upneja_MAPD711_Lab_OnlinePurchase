package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model;

import java.util.UUID;

/**
 * Created by Sergey-PC on 02.12.2017.
 */

public class Customer {
    private UUID id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String province;
    private String city;
    private String postalCode;

    public Customer(String login, String password, String firstName,
                    String lastName, String address, String province,
                    String city, String postalCode) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.province = province;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Customer(UUID id) {
        this.id = id;
    }

    public Customer() {
        this(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
