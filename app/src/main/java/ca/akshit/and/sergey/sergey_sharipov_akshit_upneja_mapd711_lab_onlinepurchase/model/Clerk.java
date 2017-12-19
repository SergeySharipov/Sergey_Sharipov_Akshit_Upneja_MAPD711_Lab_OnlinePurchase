package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model;

import java.util.UUID;

/**
 * Created by Sergey-PC on 04.12.2017.
 */

public class Clerk {
    private UUID id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;

    public Clerk(String login, String password, String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Clerk(UUID id) {
        this.id = id;
    }

    public Clerk() {
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
}
