package com.barclays.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

public class UserDTO {

	private Integer loginId;

	@NotNull(message = "{user.password.absent}")
	private String password;

	@NotNull(message = "{user.sequence.absent}")
	private String linkedAccountSequenceId;

	@NotNull(message= "{user.roleId.absent")
	 private Integer roleId;
	
	public UserDTO() {

	}

	public UserDTO(Integer loginId, @NotNull(message = "{user.password.absent}") String password,
			@NotNull(message = "{user.sequence.absent}") String linkedAccountSequenceId,
			@NotNull(message = "{user.roleId.absent") Integer roleId) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.linkedAccountSequenceId = linkedAccountSequenceId;
		this.roleId = roleId;
	}
	

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLinkedAccountSequenceId() {
		return linkedAccountSequenceId;
	}

	public void setLinkedAccountSequenceId(String linkedAccountSequenceId) {
		this.linkedAccountSequenceId = linkedAccountSequenceId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserDTO [loginId=" + loginId + ", password=" + password + ", linkedAccountSequenceId="
				+ linkedAccountSequenceId + ", roleId=" + roleId + "]";
	}

	
}
