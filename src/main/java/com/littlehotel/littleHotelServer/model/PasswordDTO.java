package com.littlehotel.littleHotelServer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * DTO to save password details for changing password
 * 
 * @author Sharad Shrestha
 *
 */
public class PasswordDTO {
	@NotNull
	private String oldPassword;

	@NotNull
	@Pattern(regexp = "^(?=.*[0-9])(?=.[a-z]).{6,32}")
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
