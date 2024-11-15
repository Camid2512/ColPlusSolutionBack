package co.edu.unbosque.ColPlusSolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.ColPlusSolution.model.LoginRecord;
import co.edu.unbosque.ColPlusSolution.model.User;
import co.edu.unbosque.ColPlusSolution.service.LoginRecordService;
import co.edu.unbosque.ColPlusSolution.service.UserService;

@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RestController
@RequestMapping("/session")
public class LoginRecordController {

	@Autowired
	private LoginRecordService logRecServ;
	@Autowired
	private UserService userServ;

	public LoginRecordController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/createsession")
	public ResponseEntity<String> createLoginRecord(@RequestParam String username) {
		try {
			User newUser = userServ.getByUsername(username);

			int status = logRecServ.createLoginRecord(newUser);

			if (status == 0) {
				return new ResponseEntity<String>("session saved succesfully", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("Error saving session", HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("ERROR Please contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<LoginRecord>> getAll() {
		try {
			List<LoginRecord> sessions = logRecServ.getAll();
			if (sessions.isEmpty()) {
				return new ResponseEntity<>(sessions, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(sessions, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getip")
	public String getMethodName() {

		return logRecServ.getPublicIp();

	}

}
