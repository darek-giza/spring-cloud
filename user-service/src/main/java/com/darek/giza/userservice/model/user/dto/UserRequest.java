package com.darek.giza.userservice.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserRequest {

    @NotBlank(message = "User first name is empty or null")
    @Indexed(name = "app_user_first_name")
    private String firstName;

    @NotBlank(message = "User last name is empty or null")
    @Indexed(name = "app_user_last_name")
    private String lastName;

}
