package com.barclays.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.dto.BillsDTO;
import com.barclays.dto.RegisteredBillersDTO;
import com.barclays.dto.UserDTO;
import com.barclays.exception.PaymentsException;
import com.barclays.service.UserService;

@RestController
@RequestMapping(value = "/pay")
@Validated
public class PaymentApi {
	
	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

	@PostMapping(value = "/registerBiller/{sequenceId}")
	public ResponseEntity<String> registerBiller(@PathVariable Integer sequenceId, @RequestBody RegisteredBillersDTO 
			registerBillersDTO)
			throws PaymentsException {
		Integer id= userService.registerBiller(sequenceId,registerBillersDTO );
		String successMessage = environment.getProperty("API.REGISTERED_BILLER");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}	
	
	//getallregisteredbillersdata
	
	@GetMapping(value = "/registeredBillers")
	public ResponseEntity<List<RegisteredBillersDTO>> getAllbillers() throws PaymentsException {
		List<RegisteredBillersDTO> billerList = userService.getAllBillers();
		return new ResponseEntity<>(billerList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/registeredBillers/{sequenceId}")
	public ResponseEntity<List<RegisteredBillersDTO>> getbillers(@PathVariable Integer sequenceId) throws PaymentsException {
		List<RegisteredBillersDTO> billerList = userService.getBillers(sequenceId);
		return new ResponseEntity<>(billerList, HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/deleteBillers/{billerSequenceId}")
	public ResponseEntity<String> deleteuser(@PathVariable Integer billerSequenceId) throws PaymentsException {
		userService.deleteBiller(billerSequenceId);
		String successMessage = environment.getProperty("API.BILLER_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PostMapping(value = "/generateBill")
	public ResponseEntity<String> generatebill(@RequestBody BillsDTO 
			billsDTO)
			throws PaymentsException {
		System.out.println(billsDTO);
		Integer id= userService.generateBill(billsDTO );
		String successMessage = environment.getProperty("API.GENERATE_BILL")+ id;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}	
	
	
	
}
