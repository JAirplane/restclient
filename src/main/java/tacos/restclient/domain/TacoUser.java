package tacos.restclient.domain;

import lombok.Data;

@Data
public class TacoUser {

    private Long id;

    private final String username;

    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;
}
