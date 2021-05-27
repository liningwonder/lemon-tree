package io.lemon.tree.validate;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotBlank(message = "name can not be null or blank")
    @Size(min = 1, max = 50, message = "the size of name is between 1 and 50")
    private String name;

    @NotBlank(message = "email can not be null or blank")
    @Email(message = "email is not email format")
    private String email;



}
