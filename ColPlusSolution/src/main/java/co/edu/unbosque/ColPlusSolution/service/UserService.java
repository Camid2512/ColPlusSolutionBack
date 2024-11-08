package co.edu.unbosque.ColPlusSolution.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.ColPlusSolution.model.Payroll;
import co.edu.unbosque.ColPlusSolution.model.User;
import co.edu.unbosque.ColPlusSolution.repository.PayrollRepository;
import co.edu.unbosque.ColPlusSolution.repository.UserRepository;

@Service
public class UserService {
	private String codeTemp = "";
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private PayrollRepository payRep;

	@Autowired
	private EmailService emailService;

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public int create(User newUser) {

		String username = newUser.getUser();
        String password = newUser.getPassword();
        String email = newUser.getEmail();
        int user_type = newUser.getUserType();

        if (findUsernameAlreadyTaken(newUser) || findEmailAlreadyTaken(newUser)) {
            return 1;
        } else {
            Integer payrollCode = newUser.getEmployee().getCode();
            Optional<Payroll> payroll = payRep.findById(payrollCode);
            
            if (payroll.isPresent()) {
                newUser.setEmployee(payroll.get());
            } else {
                return 1; 
            }

            newUser.setUser(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setUserType(user_type);
            
            // Enviar email de bienvenida y guardar el nuevo usuario
            emailService.sendWelcomeEmail(email);
            userRep.save(newUser);
            return 0;
		}
	}

	public int loginValidation(String username, String password, int user_type) {

		String userValidiation = username;
		String passwordValdiation = password;
		for (User u : getAll()) {

			if (u.getUser().equals(userValidiation)) {
				if (u.getPassword().equals(passwordValdiation)) {
					if (user_type == 0) {
						// JEFE
						return 0;
					} else {
						// EMPLEADO
						return 1;
					}

				}
			}
		}
		// NO EXISTES
		return 2;

	}

	public long count() {
		return userRep.count();
	}

	public List<User> getAll() {
		return (List<User>) userRep.findAll();
	}

	public int deleteById(Integer id) {
		Optional<User> found = userRep.findById(id);
		if (found.isPresent()) {
			userRep.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public int updateById(Integer id, User newData) {
		Optional<User> found = userRep.findById(id);
		Optional<User> newFound = userRep.findByUser(newData.getUser());

		if (found.isPresent() && !newFound.isPresent()) {
			User temp = found.get();
			temp.setUser(newData.getUser());
			temp.setPassword(newData.getPassword());
			temp.setEmail(newData.getEmail());
			userRep.save(temp);
			emailService.sendUpdateEmail(newData.getEmail());
			return 0;
		}
		if (found.isPresent() && newFound.isPresent()) {
			return 1;
		}
		if (!found.isPresent()) {
			return 2;
		} else {
			return 3;
		}
	}

	public User getById(Integer id) {
		Optional<User> found = userRep.findById(id);
		if (found.isPresent()) {
			return found.get();
		} else {
			return null;
		}
	}

	public User getByUsername(String username) {
		Optional<User> found = userRep.findByUser(username);
		if (found.isPresent()) {
			return found.get();
		} else {
			return null;
		}
	}

	public String getCodeRecovering(String email) {

		Optional<User> found = userRep.findByEmail(email);
		if (found.isPresent()) {
			codeTemp = emailService.recoverPasswordEmail(email);
			System.out.println(codeTemp);
			return codeTemp;
		} else {
			System.out.println(codeTemp);
			return codeTemp;
		}
	}

	public int recoverPassword(String code, String newPass, String email) {

		Optional<User> found = userRep.findByEmail(email);
		String codeGet = codeTemp;
		System.out.println(codeGet);
		if (found.isPresent()) {
			if (code.equals(codeGet)) {

				User temp = found.get();
				temp.setUser(found.get().getUser());
				temp.setPassword(found.get().getPassword());
				temp.setEmail(found.get().getEmail());
				userRep.save(temp);
				emailService.changedPassword(email, newPass);
				return 0;
			}
		} else {
			return 1;
		}
		return 2;
	}

	public boolean findUsernameAlreadyTaken(User userSearch) {
		Optional<User> found = userRep.findByUser(userSearch.getUser());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean findEmailAlreadyTaken(User userSearch) {
		Optional<User> found = userRep.findByEmail(userSearch.getEmail());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean findPassword(User userSearch) {

		Optional<User> found = userRep.findByPassword(userSearch.getPassword());

		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}

	}

}