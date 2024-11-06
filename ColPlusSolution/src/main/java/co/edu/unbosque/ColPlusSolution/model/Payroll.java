package co.edu.unbosque.ColPlusSolution.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payroll")
public class Payroll {

	@Id
	@Column(name = "code")
	private Integer code;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "department")
	private String department;

	@Column(name = "position")
	private String position;

	@Column(name = "hire_date")
	private Date hireDate;

	@Column(name = "health_insurance")
	private String healthInsurance;

	@Column(name = "occupational_risk_insurance")
	private String occupationalRiskInsurance;

	@Column(name = "pension")
	private String pension;

	@Column(name = "salary")
	private Double salary;

	public Payroll() {
		// TODO Auto-generated constructor stub
	}

	public Payroll(Integer code, String employeeName, String department, String position, Date hireDate,
			String healthInsurance, String occupationalRiskInsurance, String pension, Double salary) {
		this.code = code;
		this.employeeName = employeeName;
		this.department = department;
		this.position = position;
		this.hireDate = hireDate;
		this.healthInsurance = healthInsurance;
		this.occupationalRiskInsurance = occupationalRiskInsurance;
		this.pension = pension;
		this.salary = salary;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getOccupationalRiskInsurance() {
		return occupationalRiskInsurance;
	}

	public void setOccupationalRiskInsurance(String occupationalRiskInsurance) {
		this.occupationalRiskInsurance = occupationalRiskInsurance;
	}

	public String getPension() {
		return pension;
	}

	public void setPension(String pension) {
		this.pension = pension;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
