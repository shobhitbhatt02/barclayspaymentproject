package com.barclays.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.dto.BillsDTO;
import com.barclays.dto.RegisteredBillersDTO;
import com.barclays.dto.UserDTO;
import com.barclays.entity.Bills;
import com.barclays.entity.RegisteredBillers;
import com.barclays.entity.User;
import com.barclays.exception.PaymentsException;
import com.barclays.repository.BillsRepository;
import com.barclays.repository.RegisteredBillersRepository;
import com.barclays.repository.UserRespository;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRespository userRespository;

	@Autowired
	private RegisteredBillersRepository registeredBillersRepository;
	
	@Autowired
	private BillsRepository billsRepository;
	
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

	@Override
	public Integer loginUser(UserDTO UserDTO) throws PaymentsException {
		Optional<User> optional = userRespository.findById(UserDTO.getLoginId());
		User user = optional.orElseThrow(() -> new PaymentsException("Service.USERS_NOT_FOUND"));
		if(user.getPassword().equals(UserDTO.getPassword()))
		{
			return user.getLoginId();
		}
		else
		{
			throw new PaymentsException("Service.INCORRECT");
		}
	}

	@Override
	public Integer registerBiller(Integer SequenceId,RegisteredBillersDTO registerBillerDTO) throws PaymentsException {
		
		RegisteredBillers registerBiller = new RegisteredBillers();
		
		registerBiller.setBillerCode(registerBillerDTO.getBillerCode());
		registerBiller.setConsumerNumber(registerBillerDTO.getConsumerNumber());
		registerBiller.setSequenceId(SequenceId);
		registerBiller.setAutopay(registerBillerDTO.getAutopay());
		registerBiller.setAutopayLimit(registerBillerDTO.getAutopayLimit());
		
		RegisteredBillers biller2 = registeredBillersRepository.save(registerBiller);//PERSISTING IN DATABASE
		return biller2.getBillerSequenceId();
		
	}

	@Override
	public List<RegisteredBillersDTO> getAllBillers() throws PaymentsException {
	
		Iterable<RegisteredBillers> billers = registeredBillersRepository.findAll();
		List<RegisteredBillersDTO> registeredBillersDTOs = new ArrayList<>();
		billers.forEach(biller -> {
			RegisteredBillersDTO rb = new RegisteredBillersDTO();
			rb.setAutopay(biller.getAutopay());
			rb.setAutopayLimit(biller.getAutopayLimit());
			rb.setBillerCode(biller.getBillerCode());
			rb.setBillerSequenceId(biller.getBillerSequenceId());
			rb.setConsumerNumber(biller.getConsumerNumber());
			rb.setSequenceId(biller.getSequenceId());
			registeredBillersDTOs.add(rb);
		});
		if (registeredBillersDTOs.isEmpty())
			throw new PaymentsException("Service.BILLER_NOT_FOUND");
		return registeredBillersDTOs;
	}

	@Override
	public List<RegisteredBillersDTO> getBillers(Integer sequenceId) throws PaymentsException {
	
		Iterable<RegisteredBillers> billers = registeredBillersRepository.findBySequenceId(sequenceId);
		List<RegisteredBillersDTO> registeredBillersDTOs = new ArrayList<>();
		
		billers.forEach(biller -> {
			RegisteredBillersDTO rb = new RegisteredBillersDTO();
			rb.setAutopay(biller.getAutopay());
			rb.setAutopayLimit(biller.getAutopayLimit());
			rb.setBillerCode(biller.getBillerCode());
			rb.setBillerSequenceId(biller.getBillerSequenceId());
			rb.setConsumerNumber(biller.getConsumerNumber());
			rb.setSequenceId(biller.getSequenceId());
			registeredBillersDTOs.add(rb);
		});
		if (registeredBillersDTOs.isEmpty())
			throw new PaymentsException("Service.BILLER_NOT_FOUND");
		return registeredBillersDTOs;
	}

	@Override
	public void deleteBiller(Integer billerSequenceId) throws PaymentsException {
		
		Optional<RegisteredBillers> register= registeredBillersRepository.findById(billerSequenceId);
		register.orElseThrow(() -> new PaymentsException("Service.BILLER_NOT_FOUND"));
		registeredBillersRepository.deleteById(billerSequenceId);
		
	}
//bill generation by manager
	@Override
	public Integer generateBill(BillsDTO billsDTO) {
		Bills bill = new Bills();
		
		bill.setAmount(billsDTO.getAmount());
		bill.setBillerCode(billsDTO.getBillerCode());
		bill.setConsumerNumber(billsDTO.getConsumerNumber());
		bill.setDueDate(billsDTO.getDueDate());
		bill.setStatus(billsDTO.getStatus());
		
		Bills bill2 = billsRepository.save(bill); //persisting data in database
		return bill2.getBillSequenceId();
	}

}
