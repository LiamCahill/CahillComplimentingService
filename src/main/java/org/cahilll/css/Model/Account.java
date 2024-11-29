package org.cahilll.css.Model;

public class Account {
    private String email;
    private String username;
    private String password;
    // private ArrayList<Transaction> transactions;

    private static final long serialVersionUID = 5426L;

    public Account() {
    }

    public Account(String username, String password) {
        //TODO: might need to add a list of Compliments here. Doesn't make sense to have a separate compliment class.
        super();
        this.email = email;
        this.username = username;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
