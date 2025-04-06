package org.cahilll.css.Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Compliment {
    // How should I handle marking a compliment as read?
    // Should I have a method to mark a compliment as read on the DAO layer, or at the service layer?
    

    private int id;
    private String sender;
    private String receiver;
    private String message;
    private boolean used;

}
