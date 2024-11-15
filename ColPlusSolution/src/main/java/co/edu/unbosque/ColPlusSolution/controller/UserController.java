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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.ColPlusSolution.model.User;
import co.edu.unbosque.ColPlusSolution.model.Payroll;
import co.edu.unbosque.ColPlusSolution.service.PayrollService;
import co.edu.unbosque.ColPlusSolution.service.UserService;

@RestController
@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userServ;
	
	@Autowired
	private PayrollService payServ;

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(path = "/create")
	ResponseEntity<String> create(@RequestParam Integer id, @RequestParam Integer code, @RequestParam String newUsername,
			@RequestParam String newPassword, @RequestParam String newEmail, @RequestParam int newUser_type) {
		
		Payroll thisPayroll = payServ.getById(code);
		User newUser = new User(id, thisPayroll, newUsername, newPassword, newEmail, newUser_type);

		System.out.println("CREANDO USUARIO");
		int status = userServ.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creating new user, check username already taken",
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJson(@RequestBody User newUser) {

		System.out.println("CREANDO USUARIO");
		int status = userServ.create(newUser);

		if (status == 0) {
			return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error creating new user, check username already taken",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@PostMapping("/loginvalidation")
	public ResponseEntity<String> loginValidation(@RequestParam String username, @RequestParam String password) {
	    int status = userServ.loginValidation(username, password, 0); // Aquí puedes hacer el ajuste necesario para determinar el tipo de usuario

	    if (status == 0) {
	        return new ResponseEntity<>("Welcome boss", HttpStatus.OK); // Jefe
	    } else if (status == 1) {
	        return new ResponseEntity<>("Welcome employee", HttpStatus.OK); // Empleado
	    } else {
	        return new ResponseEntity<>("User/Password incorrect", HttpStatus.UNAUTHORIZED);
	    }
	}


	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAll() {

		try {

			List<User> users = userServ.getAll();
			if (users.isEmpty()) {
				return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countAll() {

		Long count = userServ.count();
		if (count == 0) {

			return new ResponseEntity<>(count, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
		}

	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<User> getById(@PathVariable Integer id) {
		User found = userServ.getById(id);
		if (found != null) {
			return new ResponseEntity<>(found, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/update")
	ResponseEntity<String> updateNew(@RequestParam Integer id, @RequestParam String newUsername,
			@RequestParam String newPassword, String newEmail, @RequestParam int newUser_type) {
		User newUser = new User(id, null, newUsername, newPassword, newEmail, newUser_type);

		int status = userServ.updateById(id, newUser);

		if (status == 0) {
			return new ResponseEntity<>("User updated successfully", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("New username already taken", HttpStatus.IM_USED);
		} else if (status == 2) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error on update", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/getrecovercode")
	ResponseEntity<String> getCodeRecovery(@RequestParam String email) {

		return new ResponseEntity<>(userServ.getCodeRecovering(email), HttpStatus.ACCEPTED);

	}

	@PutMapping(path = "/recoverpass")
	ResponseEntity<String> recoverPas(@RequestParam String code, @RequestParam String newPass,
			@RequestParam String email) {

		int status = userServ.recoverPassword(code, newPass, email);

		if (status == 0) {
			return new ResponseEntity<>("password updated successfully", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error on update", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	ResponseEntity<String> deleteById(@PathVariable Integer id) {
		int status = userServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/logincheck", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> checkLogin(@RequestBody User userCheck) {

		int status = userServ.loginValidation(userCheck.getUser(), userCheck.getPassword(), userCheck.getUserType());
		if (status == 0) {

			return new ResponseEntity<>("Welcome boss", HttpStatus.ACCEPTED);

		} else if (status == 1) {
			return new ResponseEntity<>("Welcome employee", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("User/Password incorrect", HttpStatus.NOT_ACCEPTABLE);
		}

	}

}
