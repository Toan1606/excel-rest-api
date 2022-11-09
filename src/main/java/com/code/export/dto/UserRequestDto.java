package com.code.export.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserRequestDto implements Serializable {

    @Email(message = "It has be an email")
    private String email;

    @NotEmpty(message = "Password must have length between 8 and 16 characters")
    @Length(min = 8, max = 16)
    private String password;

    @NotEmpty(message = "Firstname can not be empty")
    private String firstName;

    private String middleName;

    @NotEmpty(message = "Firstname can not be empty")
    private String lastName;

    @NotNull(message = "User must have status")
    private boolean enabled;
}
