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
		
		Integer id= userService.generateBill(billsDTO );
		String successMessage = environment.getProperty("API.GENERATE_BILL")+ id;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}	
	//shows bills for the biller
	@GetMapping(value ="/getBills/{billerCode}")
	public ResponseEntity<List<String>> getbills(@PathVariable Integer billerCode) throws PaymentsException {
		List<String> billList = userService.getBills(billerCode);
		return new ResponseEntity<>(billList, HttpStatus.OK);
	}
	//get all bills of billers
	@GetMapping(value ="/getallBills")
	public ResponseEntity<List<String>> getallbills() throws PaymentsException {
		List<String> billList = userService.getAllBills();
		return new ResponseEntity<>(billList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/manualPayment/{sequenceId}")
	public ResponseEntity<String> manualPay(@PathVariable Integer sequenceId,@RequestBody AccountTransactionDTO 
			accountTransactionDTO)
			throws PaymentsException {
		
		Integer id= userService.manualPay(sequenceId,accountTransactionDTO );
		String successMessage = environment.getProperty("API.GENERATE_BILL")+ id;
		
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}	
	
	//..........................
	
	
	@GetMapping("/transactions/export")
    public void exportToCSV(HttpServletResponse response) throws IOException, PaymentsException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<Accounts_Transaction> listUsers = userService.listall();
        response.reset();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Transaction Reference Number", "Amount", "Bill Reference Number", "Date", "Description"," SequenceId","Transaction Type"};
        String[] nameMapping = {"trans_ref_num", "amount", "bill_ref_num", "date","description", "sequence_id","transaction_type"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (Accounts_Transaction user : listUsers) {
            csvWriter.write(user, nameMapping);
        }
         
        csvWriter.close();
         
    }
	
	
	
}
