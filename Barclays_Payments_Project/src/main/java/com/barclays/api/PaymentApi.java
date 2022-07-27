package com.barclays.api;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.barclays.dto.AccountTransactionDTO;
import com.barclays.dto.BillsDTO;
import com.barclays.dto.RegisteredBillersDTO;
import com.barclays.dto.UserDTO;
import com.barclays.entity.Accounts_Transaction;
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
		
		return userService.registerBiller(sequenceId,registerBillersDTO );
		
	}	
	
	//getallregisteredbillersdata
	
	@GetMapping(value = "/registeredBillers")
	public ResponseEntity<List<RegisteredBillersDTO>> getAllbillers() throws PaymentsException {
		return userService.getAllBillers();
		
	}
	
	@GetMapping(value = "/registeredBillers/{sequenceId}")
	public ResponseEntity<List<RegisteredBillersDTO>> getbillers(@PathVariable Integer sequenceId) throws PaymentsException {
		return userService.getBillers(sequenceId);
		
	}
	

	@DeleteMapping(value = "/deleteBillers/{billerSequenceId}")
	public ResponseEntity<String> deletebiller(@PathVariable Integer billerSequenceId) throws PaymentsException {
		return userService.deleteBiller(billerSequenceId);
		
	}
	
	@PostMapping(value = "/generateBill")
	public ResponseEntity<String> generatebill(@RequestBody BillsDTO 
			billsDTO)
			throws PaymentsException {
		
		return userService.generateBill(billsDTO );
		
	}	
	//shows bills for the biller
	@GetMapping(value ="/getBills/{billerCode}")
	public ResponseEntity<List<String>> getbills(@PathVariable Integer billerCode) throws PaymentsException {
		return userService.getBills(billerCode);
		
	}
	//get all bills of billers
	@GetMapping(value ="/getallBills")
	public ResponseEntity<List<String>> getallbills() throws PaymentsException {
		return userService.getAllBills();
		
	}
	
	@PostMapping(value = "/payment/{sequenceId}")
	public ResponseEntity<String> manualPay(@PathVariable Integer sequenceId,@RequestBody AccountTransactionDTO 
			accountTransactionDTO)
			throws PaymentsException {
		
		return userService.manualPay(sequenceId,accountTransactionDTO );
		
	}	
	
	//..........................
	
	
	@GetMapping("/transactions/export")
    public void exportToCSV(HttpServletResponse response) throws IOException, PaymentsException {
        response.setContentType("text/csv");
        
         
        userService.listall(response);
        
         
    }
	
	
	
}
