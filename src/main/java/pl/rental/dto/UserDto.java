package pl.rental.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String street;
    private String city;
    private String zipCode;
    private String country;

}
