package co.edu.unbosque.ColPlusSolution.controller;

import java.util.Date;
import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.ColPlusSolution.model.Payroll;
import co.edu.unbosque.ColPlusSolution.service.PayrollService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RequestMapping("/payroll")
public class PayrollController {

	@Autowired
	private PayrollService payServ;

	public PayrollController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(path = "/create")
	ResponseEntity<String> create(@RequestParam Integer code, @RequestParam String employeeName,
	        @RequestParam String department, @RequestParam String position, @RequestParam String hireDate,
	        @RequestParam String healthInsurance, @RequestParam String occupationalRiskInsurance,
	        @RequestParam String pension, @RequestParam Double salary) {
	    
	    // Convertir el String `hireDate` a `Date`
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date parsedHireDate;
	    try {
	        parsedHireDate = dateFormat.parse(hireDate);
	    } catch (ParseException e) {
	        return new ResponseEntity<>("Error parsing hireDate", HttpStatus.BAD_REQUEST);
	    }

	    Payroll newPayroll = new Payroll(code, employeeName, department, position, parsedHireDate, healthInsurance,
	            occupationalRiskInsurance, pension, salary);

	    System.out.println("CREANDO EMPLEADO");
	    int status = payServ.create(newPayroll);

	    if (status == 0) {
	        return new ResponseEntity<>("Employee added successfully", HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>("Error adding an employee, check code", HttpStatus.NOT_ACCEPTABLE);
	    }
	}


	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJson(@RequestBody Payroll newPayroll) {

		System.out.println("CREANDO EMPLEADO");
		int status = payServ.create(newPayroll);

		if (status == 0) {
			return new ResponseEntity<String>("Employee added successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error adding an employee, check code", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping(path = "/createwithexcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Payroll>> createWithExcel(@RequestParam("file") MultipartFile file) {
		try {
			List<Payroll> payrolls = payServ.saveFromExcel(file.getInputStream());
			return new ResponseEntity<>(payrolls, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Payroll>> getAll() {

		try {

			List<Payroll> employees = payServ.getAll();
			if (employees.isEmpty()) {
				return new ResponseEntity<>(employees, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(employees, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countAll() {

		Long count = payServ.count();
		if (count == 0) {

			return new ResponseEntity<>(count, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
		}

	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Payroll> getById(@PathVariable Integer id) {
		Payroll found = payServ.getById(id);
		if (found != null) {
			return new ResponseEntity<>(found, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/update")
	ResponseEntity<String> updateNew(@RequestParam Integer code, @RequestParam String employeeName,
			@RequestParam String department, String position, @RequestParam String hireDate,
			@RequestParam String healthInsurance, @RequestParam String occupationalRiskInsurance,
			@RequestParam String pension, @RequestParam Double salary) {
		
		// Convertir el String `hireDate` a `Date`
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date parsedHireDate;
	    try {
	        parsedHireDate = dateFormat.parse(hireDate);
	    } catch (ParseException e) {
	        return new ResponseEntity<>("Error parsing hireDate", HttpStatus.BAD_REQUEST);
	    }
	    
		Payroll newPayroll = new Payroll(code, employeeName, department, position, parsedHireDate, healthInsurance,
				occupationalRiskInsurance, pension, salary);

		int status = payServ.updateById(code, newPayroll);

		if (status == 0) {
			return new ResponseEntity<>("Employee data updated succesfully", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error on update", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	ResponseEntity<String> deleteById(@PathVariable Integer id) {
		int status = payServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<>("Employee deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}
}
