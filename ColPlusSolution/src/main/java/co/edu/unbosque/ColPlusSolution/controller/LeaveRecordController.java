package co.edu.unbosque.ColPlusSolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.ColPlusSolution.model.LeaveRecord;
import co.edu.unbosque.ColPlusSolution.service.LeaveRecordService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RequestMapping("/leaverecord")
public class LeaveRecordController {

	@Autowired
	private LeaveRecordService leaServ;

	public LeaveRecordController() {
		// TODO Auto-generated constructor stub
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
