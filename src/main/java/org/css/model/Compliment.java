package org.css.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Compliment implements Serializable {
    private String message;
    private String dateSent;
    private String sender;
    private String receiver;

    @Serial
    private static final long serialVersionUID = -35536L;

    public Compliment(){}

    public Compliment(String message, String dateSent, String sender, String receiver) {
        this.message = message;
        this.dateSent = dateSent;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compliment that = (Compliment) o;
        return Objects.equals(message, that.message) && Objects.equals(dateSent, that.dateSent) && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, dateSent, sender, receiver);
    }

    @Override
    public String toString() {
        return "Compliment{" +
                "message='" + message + '\'' +
                ", dateSent='" + dateSent + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
