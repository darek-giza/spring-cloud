package com.darek.giza.userservice.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "app_user")
public class User {

    @Id
    private String id;

    @NotBlank(message = "User email is empty or null")
    @Email(message = "Email is not in proper format")
    @Indexed(name = "app_user_email", unique = true)
    private String email;

    @NotBlank(message = "User password is empty or null")
    @Size(min = 8)
    private String password;

    @NotBlank(message = "User first name is empty or null")
    @Indexed(name = "app_user_first_name")
    private String firstName;

    @NotBlank(message = "User last name is empty or null")
    @Indexed(name = "app_user_last_name")
    private String lastName;

    private Date createdAt;
}
