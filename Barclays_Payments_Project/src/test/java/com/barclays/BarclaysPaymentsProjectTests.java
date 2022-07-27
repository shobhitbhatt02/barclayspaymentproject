package com.barclays;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.barclays.dto.UserDTO;
import com.barclays.entity.User;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.UserRespository;
import com.barclays.service.UserService;
import com.barclays.service.UserServiceImpl;

@SpringBootTest
class BarclaysPaymentsProjectTests {
	
	@Mock
	UserRespository userRepository;
	
	@InjectMocks
	UserService userService = new UserServiceImpl();
	
	@Test
	public void authenticateUserTestValidCredentials() throws PaymentsException {
		UserDTO user = new UserDTO();
		user.setLoginId(123456);
		user.setPassword("123");
		Mockito.when(userRepository.findById(123456));
		ResponseEntity<String> actual = userService.loginUser(user);
		Assertions.assertEquals("SUCCESS", actual);
	} 
	@Test
	public void authenticateUserTestInValidCredentials() throws PaymentsException {
		UserDTO User = new UserDTO();
		User.setLoginId(123456);
		User.setPassword("monica12");
		
		UserDTO fromRepository = new UserDTO();
		fromRepository.setLoginId(123456);
		fromRepository.setPassword("123");
		
		Mockito.doReturn(fromRepository).when(userRepository.findById(123456));
	    PaymentsException exception=Assertions.assertThrows(PaymentsException.class,()->userService.loginUser(User));
		Assertions.assertEquals("Service.INCORRECT", exception.getMessage());
	}
}

