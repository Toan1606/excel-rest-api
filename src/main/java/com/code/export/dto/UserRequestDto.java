package com.code.export.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserRequestDto implements Serializable {

    @Email
    private String email;

    @NotEmpty
    @Length(min = 8, max = 16)
    private String password;

    @NotEmpty
    private String firstName;

    private String middleName;

    @NotEmpty
    private String lastName;

    @NotNull
    private boolean enabled;
}
