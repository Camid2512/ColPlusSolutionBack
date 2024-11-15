package co.edu.unbosque.ColPlusSolution.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
			payRep.save(newPayroll);
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
			payRep.save(temp);
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

	public List<Payroll> saveFromExcel(InputStream file) {
		List<Payroll> payrollList = new ArrayList<>();
		try (Workbook workbook = new XSSFWorkbook(file)) {
			Sheet sheet = workbook.getSheetAt(0);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				Payroll payroll = new Payroll();

				if (row.getCell(0).getCellType() == CellType.NUMERIC) {
					payroll.setCode((int) row.getCell(0).getNumericCellValue());
				}

				if (row.getCell(1).getCellType() == CellType.STRING) {
					payroll.setEmployeeName(row.getCell(1).getStringCellValue());
				}

				if (row.getCell(2).getCellType() == CellType.STRING) {
					payroll.setDepartment(row.getCell(2).getStringCellValue());
				}

				if (row.getCell(3).getCellType() == CellType.STRING) {
					payroll.setPosition(row.getCell(3).getStringCellValue());
				}

				if (row.getCell(4).getCellType() == CellType.NUMERIC) {
					String dateStr = String.valueOf((long) row.getCell(4).getNumericCellValue());
					payroll.setHireDate(dateFormat.parse(dateStr));
				}

				if (row.getCell(5).getCellType() == CellType.STRING) {
					payroll.setHealthInsurance(row.getCell(5).getStringCellValue());
				}

				if (row.getCell(6).getCellType() == CellType.STRING) {
					payroll.setOccupationalRiskInsurance(row.getCell(6).getStringCellValue());
				}

				if (row.getCell(7).getCellType() == CellType.STRING) {
					payroll.setPension(row.getCell(7).getStringCellValue());
				}

				if (row.getCell(8).getCellType() == CellType.NUMERIC) {
					payroll.setSalary(row.getCell(8).getNumericCellValue());
				}

				if (!payRep.existsById(payroll.getCode())) {
					payRep.save(payroll);
					payrollList.add(payroll);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payrollList;
	}

}
