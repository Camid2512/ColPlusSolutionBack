package co.edu.unbosque.ColPlusSolution.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.ColPlusSolution.model.Payroll;
import co.edu.unbosque.ColPlusSolution.repository.PayrollRepository;

@Service
public class PayrollService {

	@Autowired
	private PayrollRepository payRep;

	public PayrollService() {
		// TODO Auto-generated constructor stub
	}

	public int create(Payroll newPayroll) {
		Integer code = newPayroll.getCode();
		String employeeName = newPayroll.getEmployeeName();
		String department = newPayroll.getDepartment();
		String poisition = newPayroll.getPosition();
		Date hireDate = newPayroll.getHireDate();
		String healthInsurance = newPayroll.getHealthInsurance();
		String occupationalRiskInsurance = newPayroll.getOccupationalRiskInsurance();
		String pension = newPayroll.getPension();
		Double salary = newPayroll.getSalary();

		if (payRep.existsById(code)) {
			return 1;
		} else {
			newPayroll.setCode(code);
			newPayroll.setEmployeeName(employeeName);
			newPayroll.setDepartment(department);
			newPayroll.setPosition(poisition);
			newPayroll.setHireDate(hireDate);
			newPayroll.setHealthInsurance(healthInsurance);
			newPayroll.setOccupationalRiskInsurance(occupationalRiskInsurance);
			newPayroll.setPension(pension);
			newPayroll.setSalary(salary);
			return 0;

		}

	}

	public long count() {
		return payRep.count();
	}

	public List<Payroll> getAll() {
		return (List<Payroll>) payRep.findAll();
	}

	public int deleteById(Integer code) {
		Optional<Payroll> found = payRep.findById(code);
		if (found.isPresent()) {
			payRep.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public int updateById(Integer id, Payroll newPayroll) {

		Optional<Payroll> found = payRep.findById(id);
		if (found.isPresent()) {
			Payroll temp = found.get();
			temp.setCode(newPayroll.getCode());
			temp.setEmployeeName(newPayroll.getEmployeeName());
			temp.setDepartment(newPayroll.getDepartment());
			temp.setPosition(newPayroll.getPosition());
			temp.setHireDate(newPayroll.getHireDate());
			temp.setHealthInsurance(newPayroll.getHealthInsurance());
			temp.setOccupationalRiskInsurance(newPayroll.getOccupationalRiskInsurance());
			temp.setPension(newPayroll.getPension());
			temp.setSalary(newPayroll.getSalary());
			return 0;

		}
		if (!found.isPresent()) {
			return 1;
		}
		return 2;

	}

	public Payroll getById(Integer code) {
		Optional<Payroll> found = payRep.findById(code);
		if (found.isPresent()) {
			return found.get();
		} else {
			return null;
		}
	}

}
