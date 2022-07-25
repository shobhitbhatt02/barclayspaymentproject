package com.barclays.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.dto.UserDTO;
import com.barclays.entity.User;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.UserRespository;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRespository userRespository;

	@Override
	public UserDTO getUser(Integer userId) throws PaymentsException {
		Optional<User> optional = userRespository.findById(userId);
		User user = optional.orElseThrow(() -> new PaymentsException("Service.CUSTOMER_NOT_FOUND"));
		UserDTO user2 = new UserDTO();
		user2.setLoginId(user.getLoginId());
		user2.setPassword(user.getPassword());
		user2.setRoleId(user.getRoleId());
		
		user2.setLinkedAccountSequenceId(user.getLinkedAccountSequenceId());
		
		return user2;
	}

	@Override
	public Integer addUser(UserDTO userDTO) throws PaymentsException {
		User user = new User();
		user.setLoginId(userDTO.getLoginId());
		user.setPassword(userDTO.getPassword());
		user.setRoleName(userDTO.getRoleName());
		user.setLinkedAccountSequenceId(userDTO.getLinkedAccountSequenceId());
		if(userDTO.getRoleName().equals("Bank_Manager"))
		{
			user.setRoleId(1);
		}
		else
		{
			user.setRoleId(2);
		}
		
		User user2 = userRespository.save(user);
		return user2.getLoginId();
	}

//	@Override
//	public void updateUser(Integer userId, String emailId) throws PaymentsException {
//		Optional<User> user = userRespository.findById(userId);
//		User c = user.orElseThrow(() -> new PaymentsException("Service.CUSTOMER_NOT_FOUND"));
//		c.setEmailId(emailId);
//	}

	@Override
	public void deleteUser(Integer loginId) throws PaymentsException {
		Optional<User> user = userRespository.findById(loginId);
		user.orElseThrow(() -> new PaymentsException("Service.CUSTOMER_NOT_FOUND"));
		userRespository.deleteById(loginId);
	}

	@Override
	public List<UserDTO> getAllUsers() throws PaymentsException {
		Iterable<User> users = userRespository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		users.forEach(user -> {
			UserDTO us = new UserDTO();
			us.setLoginId(user.getLoginId());
			us.setPassword(user.getPassword());
			us.setRoleId(user.getRoleId());
			us.setLinkedAccountSequenceId(user.getLinkedAccountSequenceId());
			userDTOs.add(us);
		});
		if (userDTOs.isEmpty())
			throw new PaymentsException("Service.USERS_NOT_FOUND");
		return userDTOs;
	}

}
