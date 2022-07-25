package com.barclays.service;

import java.util.List;

import com.barclays.dto.UserDTO;
import com.barclays.exception.PaymentsException;



public interface UserService {
	public Integer addUser(UserDTO UserDTO) throws PaymentsException;
	public UserDTO getUser(Integer UserId) throws PaymentsException;
	//public void updateUser(Integer UserId, String emailId)throws PaymentsException;
	public void deleteUser(Integer UserId)throws PaymentsException;
	public List<UserDTO> getAllUsers() throws PaymentsException;
}
