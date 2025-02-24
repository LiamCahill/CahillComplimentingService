package org.cahilll.css.Controller;

import org.cahilll.css.Controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.print("\n------------------\nthe matrix has you\n------------------\n");
        new Controller().console();
    }
}
