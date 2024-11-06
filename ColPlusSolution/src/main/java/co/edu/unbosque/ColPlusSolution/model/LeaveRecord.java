package co.edu.unbosque.ColPlusSolution.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leave_record")
public class LeaveRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_record_id")
	private Integer employeeCode;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "code", nullable = false, referencedColumnName = "code")
	private Payroll employee;

	@Column(name = "on_vacation")
	private Boolean onVacation;

	@Column(name = "on_sick_leave")
	private Boolean onSickLeave;

	@Column(name = "days_worked_this_month")
	private Integer daysWorkedThisMonth;

	@Column(name = "sick_days")
	private Integer sickDays;

	@Column(name = "vacation_days")
	private Integer vacationDays;

	@Column(name = "vacation_start_date")
	private Date vacationStartDate;

	@Column(name = "vacation_end_date")
	private Date vacationEndDate;

	@Column(name = "sick_leave_start_date")
	private Date sickLeaveStartDate;

	@Column(name = "sick_leave_end_date")
	private Date sickLeaveEndDate;

	@Column(name = "bonus")
	private Double bonus;

	@Column(name = "transport_allowance")
	private Double transportAllowance;

	public LeaveRecord() {
		// TODO Auto-generated constructor stub
	}

	public LeaveRecord(Integer employeeCode, Payroll employee, Boolean onVacation, Boolean onSickLeave,
			Integer daysWorkedThisMonth, Integer sickDays, Integer vacationDays, Date vacationStartDate,
			Date vacationEndDate, Date sickLeaveStartDate, Date sickLeaveEndDate, Double bonus,
			Double transportAllowance) {
		super();
		this.employeeCode = employeeCode;
		this.employee = employee;
		this.onVacation = onVacation;
		this.onSickLeave = onSickLeave;
		this.daysWorkedThisMonth = daysWorkedThisMonth;
		this.sickDays = sickDays;
		this.vacationDays = vacationDays;
		this.vacationStartDate = vacationStartDate;
		this.vacationEndDate = vacationEndDate;
		this.sickLeaveStartDate = sickLeaveStartDate;
		this.sickLeaveEndDate = sickLeaveEndDate;
		this.bonus = bonus;
		this.transportAllowance = transportAllowance;
	}

	public Integer getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(Integer employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Boolean getOnVacation() {
		return onVacation;
	}

	public void setOnVacation(Boolean onVacation) {
		this.onVacation = onVacation;
	}

	public Payroll getEmployee() {
		return employee;
	}

	public void setEmployee(Payroll employee) {
		this.employee = employee;
	}

	public Boolean getOnSickLeave() {
		return onSickLeave;
	}

	public void setOnSickLeave(Boolean onSickLeave) {
		this.onSickLeave = onSickLeave;
	}

	public Integer getDaysWorkedThisMonth() {
		return daysWorkedThisMonth;
	}

	public void setDaysWorkedThisMonth(Integer daysWorkedThisMonth) {
		this.daysWorkedThisMonth = daysWorkedThisMonth;
	}

	public Integer getSickDays() {
		return sickDays;
	}

	public void setSickDays(Integer sickDays) {
		this.sickDays = sickDays;
	}

	public Integer getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(Integer vacationDays) {
		this.vacationDays = vacationDays;
	}

	public Date getVacationStartDate() {
		return vacationStartDate;
	}

	public void setVacationStartDate(Date vacationStartDate) {
		this.vacationStartDate = vacationStartDate;
	}

	public Date getVacationEndDate() {
		return vacationEndDate;
	}

	public void setVacationEndDate(Date vacationEndDate) {
		this.vacationEndDate = vacationEndDate;
	}

	public Date getSickLeaveStartDate() {
		return sickLeaveStartDate;
	}

	public void setSickLeaveStartDate(Date sickLeaveStartDate) {
		this.sickLeaveStartDate = sickLeaveStartDate;
	}

	public Date getSickLeaveEndDate() {
		return sickLeaveEndDate;
	}

	public void setSickLeaveEndDate(Date sickLeaveEndDate) {
		this.sickLeaveEndDate = sickLeaveEndDate;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getTransportAllowance() {
		return transportAllowance;
	}

	public void setTransportAllowance(Double transportAllowance) {
		this.transportAllowance = transportAllowance;
	}

}
