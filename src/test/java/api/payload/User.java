package api.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private String firstName;
    private String lastName;
    private int totalPrice;
    private boolean depositPaid;
    private String[] bookingDates;
    private String additionalNeeds;
}

