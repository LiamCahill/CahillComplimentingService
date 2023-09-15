package org.css.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Account implements Serializable {
    String username;
    String status;
    private String password;
    private ArrayList<Compliment> compliments;
    @Serial
    private static final long serialVersionUID = 5426L;

    public Account() {
    }

    public Account(String username, String status, String password, ArrayList<Compliment> compliments) {
        this.username = username;
        this.status = status;
        this.password = password;
        this.compliments = compliments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Compliment> getCompliments() {
        return compliments;
    }

    public void setCompliments(ArrayList<Compliment> compliments) {
        this.compliments = compliments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) && Objects.equals(status, account.status) && Objects.equals(password, account.password) && Objects.equals(compliments, account.compliments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, status, password, compliments);
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                ", compliments=" + compliments +
                '}';
    }
}
