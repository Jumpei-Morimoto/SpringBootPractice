package com.example.demo.entity;


import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ContactUpdateRequest  implements Serializable{
	
	@NotBlank
    private String lastName;
	
	@NotBlank
    private String firstName;
	
	@NotBlank
	  @Email
    private String email;
	
	@NotBlank
	@Size(min = 10, max = 11)
    private String phone;
	
	@NotBlank
	@Pattern(regexp = "[0-9]{3}[-]{0,1}[0-9]{4}")
    private String zipCode;
	
	@NotBlank
    private String address;
	
	@NotBlank
    private String buildingName;
	
	@NotEmpty
    private String contactType;
	
	@NotBlank
    private String body;
	
	@NotNull
	private Long id;

}
