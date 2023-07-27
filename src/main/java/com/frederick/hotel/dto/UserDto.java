package com.frederick.hotel.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserDto {
    private long id;
    @NotEmpty(message = "Email should not be empty")
    private String email;
    private String username;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty(message = "First name should not be empty")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty")
    private String lastName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<RoleDto> roles;
    private List<BookingDto> bookingDtoList;
}
