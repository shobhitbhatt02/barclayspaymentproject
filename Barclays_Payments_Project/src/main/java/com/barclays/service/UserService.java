package com.barclays.service;

import java.util.List;

import com.barclays.dto.AccountTransactionDTO;
import com.barclays.dto.BillsDTO;
import com.barclays.dto.RegisteredBillersDTO;
import com.barclays.dto.UserDTO;
import com.barclays.exception.PaymentsException;



public interface UserService {
	public Integer addUser(UserDTO UserDTO) throws PaymentsException;
	public Integer loginUser(UserDTO UserDTO) throws PaymentsException;
	public UserDTO getUser(Integer UserId) throws PaymentsException;
	//public void updateUser(Integer UserId, String emailId)throws PaymentsException;
	public void deleteUser(Integer UserId)throws PaymentsException;
	public List<UserDTO> getAllUsers() throws PaymentsException;
	
	
	public Integer registerBiller(Integer SequenceId,RegisteredBillersDTO registerBillerDTO) throws PaymentsException;
	public List<RegisteredBillersDTO> getAllBillers() throws PaymentsException;

	public List<RegisteredBillersDTO> getBillers(Integer sequenceId) throws PaymentsException;
	public void deleteBiller(Integer billerSequenceId)throws PaymentsException;
	
	public Integer generateBill(BillsDTO billsDTO) throws PaymentsException;
	
	public Integer manualPay(Integer sequenceId,AccountTransactionDTO accountTransactionDTO)throws PaymentsException;
	
}
