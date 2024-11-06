package co.edu.unbosque.ColPlusSolution.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "code", nullable = false, referencedColumnName = "code")
	private Payroll employee;

	@Column(name = "user")
	private String user;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "user_type")
	private int userType;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer userId, Payroll employee, String user, String password, String email, int userType) {
		super();
		this.userId = userId;
		this.employee = employee;
		this.user = user;
		this.password = password;
		this.email = email;
		this.userType = userType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Payroll getEmployee() {
		return employee;
	}

	public void setEmployee(Payroll employee) {
		this.employee = employee;
	}

}
