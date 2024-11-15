package co.edu.unbosque.ColPlusSolution.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.ColPlusSolution.model.LeaveRecord;
import co.edu.unbosque.ColPlusSolution.model.Payroll;
import co.edu.unbosque.ColPlusSolution.service.LeaveRecordService;
import co.edu.unbosque.ColPlusSolution.service.PayrollService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RequestMapping("/leaverecord")
public class LeaveRecordController {

	@Autowired
	private LeaveRecordService leaServ;
	
	@Autowired
	private PayrollService payServ;

	public LeaveRecordController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> create(
	        @RequestParam("id") Integer id,
	        @RequestParam("employeeCode") Integer employeeCode,
	        @RequestParam("onVacation") Boolean onVacation,
	        @RequestParam("onSickLeave") Boolean onSickLeave,
	        @RequestParam("daysWorkedThisMonth") Integer daysWorkedThisMonth,
	        @RequestParam("sickDays") Integer sickDays,
	        @RequestParam("vacationDays") Integer vacationDays,
	        @RequestParam(value = "vacationStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date vacationStartDate,
	        @RequestParam(value = "vacationEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date vacationEndDate,
	        @RequestParam(value = "sickLeaveStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date sickLeaveStartDate,
	        @RequestParam(value = "sickLeaveEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date sickLeaveEndDate,
	        @RequestParam("bonus") Double bonus,
	        @RequestParam("transportAllowance") Double transportAllowance) {

		Payroll thisPayroll = payServ.getById(employeeCode);
	    LeaveRecord newLeaveRecord = new LeaveRecord();
	    newLeaveRecord.setEmployee(thisPayroll);
	    newLeaveRecord.setOnVacation(onVacation);
	    newLeaveRecord.setOnSickLeave(onSickLeave);
	    newLeaveRecord.setDaysWorkedThisMonth(daysWorkedThisMonth);
	    newLeaveRecord.setSickDays(sickDays);
	    newLeaveRecord.setVacationDays(vacationDays);
	    newLeaveRecord.setVacationStartDate(vacationStartDate);
	    newLeaveRecord.setVacationEndDate(vacationEndDate);
	    newLeaveRecord.setSickLeaveStartDate(sickLeaveStartDate);
	    newLeaveRecord.setSickLeaveEndDate(sickLeaveEndDate);
	    newLeaveRecord.setBonus(bonus);
	    newLeaveRecord.setTransportAllowance(transportAllowance);

	    System.out.println("CREANDO EMPLEADO");
		int status = leaServ.create(newLeaveRecord);

		if (status == 0) {
			return new ResponseEntity<String>("Leave Record added successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error adding a leave record, check code", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJson(@RequestBody LeaveRecord newLeaveRecord) {

		System.out.println("CREANDO EMPLEADO");
		int status = leaServ.create(newLeaveRecord);

		if (status == 0) {
			return new ResponseEntity<String>("Leave Record added successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error adding a leave record, check code", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping(path = "/createwithexcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<LeaveRecord>> createWithExcel(@RequestParam("file") MultipartFile file) {
		try {
			List<LeaveRecord> leaveRecords = leaServ.saveFromExcel(file.getInputStream());
			return new ResponseEntity<>(leaveRecords, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<LeaveRecord>> getAll() {

		try {

			List<LeaveRecord> leaveRecords = leaServ.getAll();
			if (leaveRecords.isEmpty()) {
				return new ResponseEntity<>(leaveRecords, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(leaveRecords, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countAll() {

		Long count = leaServ.count();
		if (count == 0) {

			return new ResponseEntity<>(count, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
		}

	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<LeaveRecord> getById(@PathVariable Integer id) {
		LeaveRecord found = leaServ.getById(id);
		if (found != null) {
			return new ResponseEntity<>(found, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> updateLeaveRecord(
	        @RequestParam("id") Integer id,
	        @RequestParam("employeeCode") Integer employeeCode,
	        @RequestParam("onVacation") Boolean onVacation,
	        @RequestParam("onSickLeave") Boolean onSickLeave,
	        @RequestParam("daysWorkedThisMonth") Integer daysWorkedThisMonth,
	        @RequestParam("sickDays") Integer sickDays,
	        @RequestParam("vacationDays") Integer vacationDays,
	        @RequestParam(value = "vacationStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date vacationStartDate,
	        @RequestParam(value = "vacationEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date vacationEndDate,
	        @RequestParam(value = "sickLeaveStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date sickLeaveStartDate,
	        @RequestParam(value = "sickLeaveEndDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date sickLeaveEndDate,
	        @RequestParam("bonus") Double bonus,
	        @RequestParam("transportAllowance") Double transportAllowance) {

	    // Crear nuevo registro de ausencias con los parámetros recibidos
	    LeaveRecord newLeaveRecord = new LeaveRecord();
	    newLeaveRecord.setEmployee(leaServ.getEmployeeFromCode(employeeCode));
	    newLeaveRecord.setOnVacation(onVacation);
	    newLeaveRecord.setOnSickLeave(onSickLeave);
	    newLeaveRecord.setDaysWorkedThisMonth(daysWorkedThisMonth);
	    newLeaveRecord.setSickDays(sickDays);
	    newLeaveRecord.setVacationDays(vacationDays);
	    newLeaveRecord.setVacationStartDate(vacationStartDate);
	    newLeaveRecord.setVacationEndDate(vacationEndDate);
	    newLeaveRecord.setSickLeaveStartDate(sickLeaveStartDate);
	    newLeaveRecord.setSickLeaveEndDate(sickLeaveEndDate);
	    newLeaveRecord.setBonus(bonus);
	    newLeaveRecord.setTransportAllowance(transportAllowance);

	    // Actualizar registro existente
	    int status = leaServ.updateById(id, newLeaveRecord);
	    if (status == 0) {
	        return new ResponseEntity<>("Leave record updated successfully", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error updating leave record, check the ID", HttpStatus.NOT_FOUND);
	    }
	}


	@DeleteMapping("/deletebyid/{id}")
	ResponseEntity<String> deleteById(@PathVariable Integer id) {
		int status = leaServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<>("Leave record deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}
}
