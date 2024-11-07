package co.edu.unbosque.ColPlusSolution.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.ColPlusSolution.model.LeaveRecord;
import co.edu.unbosque.ColPlusSolution.model.Payroll;
import co.edu.unbosque.ColPlusSolution.repository.LeaveRecordRepository;

@Service
public class LeaveRecordService {

	@Autowired
	private LeaveRecordRepository leaveRecordRep;

	@Autowired
	private PayrollService payServ;

	public LeaveRecordService() {
		// Default constructor
	}

	public int create(LeaveRecord newLeaveRecord) {
		Payroll employee = newLeaveRecord.getEmployee();
		Boolean onVacation = newLeaveRecord.getOnVacation();
		Boolean onSickLeave = newLeaveRecord.getOnSickLeave();
		Integer daysWorkedThisMonth = newLeaveRecord.getDaysWorkedThisMonth();
		Integer sickDays = newLeaveRecord.getSickDays();
		Integer vacationDays = newLeaveRecord.getVacationDays();
		Date vacationStartDate = newLeaveRecord.getVacationStartDate();
		Date vacationEndDate = newLeaveRecord.getVacationEndDate();
		Date sickLeaveStartDate = newLeaveRecord.getSickLeaveStartDate();
		Date sickLeaveEndDate = newLeaveRecord.getSickLeaveEndDate();
		Double bonus = newLeaveRecord.getBonus();
		Double transportAllowance = newLeaveRecord.getTransportAllowance();

		if (leaveRecordRep.existsById(newLeaveRecord.getEmployeeCode())) {
			return 1;
		} else {
			newLeaveRecord.setEmployee(employee);
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
			leaveRecordRep.save(newLeaveRecord);
			return 0;
		}
	}

	public List<LeaveRecord> saveFromExcel(InputStream file) {
		List<LeaveRecord> leaveRecordllList = new ArrayList<>();
		Payroll employeeSave = new Payroll();
		try (Workbook workbook = new XSSFWorkbook(file)) {
			Sheet sheet = workbook.getSheetAt(1); // Accede a "Table 3"
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue; // Omitir encabezado
				}
				LeaveRecord leaveRecord = new LeaveRecord();

				// CÓDIGO de empleado
				if (row.getCell(0).getCellType() == CellType.NUMERIC) {
					Integer employee_code = (int) row.getCell(0).getNumericCellValue();
					employeeSave = getEmployeeFromCode(employee_code);
					leaveRecord.setEmployee(employeeSave);
				}

				// NOVEDAD INCAPACIDAD
				if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
					leaveRecord.setOnSickLeave("X".equals(row.getCell(1).getStringCellValue()));
				}

				// NOVEDAD VACACIONES
				if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
					leaveRecord.setOnVacation("X".equals(row.getCell(2).getStringCellValue()));
				}

				// NUMERO DIAS TRABAJADOS EN EL MES
				if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
					leaveRecord.setDaysWorkedThisMonth((int) row.getCell(3).getNumericCellValue());
				}

				// NUMERO DIAS INCAPACIDADES EN EL MES
				if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
					leaveRecord.setSickDays((int) row.getCell(4).getNumericCellValue());
				}

				// NUMERO DE DIAS VACACIONES
				if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
					leaveRecord.setVacationDays((int) row.getCell(5).getNumericCellValue());
				}

				// FECHA DE INICIO DE VACACIONES
				if (row.getCell(6) != null) {
					if (row.getCell(6).getCellType() == CellType.NUMERIC) {
						leaveRecord.setVacationStartDate(row.getCell(6).getDateCellValue());
					} else if (row.getCell(6).getCellType() == CellType.STRING) {
						String dateStr = row.getCell(6).getStringCellValue();
						leaveRecord.setVacationStartDate(dateFormat.parse(dateStr));
					}
				}

				// FECHA TERMINACION DE VACACIONES
				if (row.getCell(7) != null) {
					if (row.getCell(7).getCellType() == CellType.NUMERIC) {
						leaveRecord.setVacationEndDate(row.getCell(7).getDateCellValue());
					} else if (row.getCell(7).getCellType() == CellType.STRING) {
						String dateStr = row.getCell(7).getStringCellValue();
						leaveRecord.setVacationEndDate(dateFormat.parse(dateStr));
					}
				}

				// FECHA INICIO INCAPACIDAD
				if (row.getCell(8) != null) {
					if (row.getCell(8).getCellType() == CellType.NUMERIC) {
						leaveRecord.setSickLeaveStartDate(row.getCell(8).getDateCellValue());
					} else if (row.getCell(8).getCellType() == CellType.STRING) {
						String dateStr = row.getCell(8).getStringCellValue();
						leaveRecord.setSickLeaveStartDate(dateFormat.parse(dateStr));
					}
				}

				// FECHA TERMINACIÓN INCAPACIDAD
				if (row.getCell(9) != null) {
					if (row.getCell(9).getCellType() == CellType.NUMERIC) {
						leaveRecord.setSickLeaveEndDate(row.getCell(9).getDateCellValue());
					} else if (row.getCell(9).getCellType() == CellType.STRING) {
						String dateStr = row.getCell(9).getStringCellValue();
						leaveRecord.setSickLeaveEndDate(dateFormat.parse(dateStr));
					}
				}

				// BONIFICACIÓN
				if (row.getCell(10) != null && row.getCell(10).getCellType() == CellType.NUMERIC) {
					leaveRecord.setBonus(row.getCell(10).getNumericCellValue());
				}

				// TRANSPORTE
				if (row.getCell(11) != null && row.getCell(11).getCellType() == CellType.NUMERIC) {
					leaveRecord.setTransportAllowance(row.getCell(11).getNumericCellValue());
				}

				leaveRecordRep.save(leaveRecord); // Guardar en la base de datos
				leaveRecordllList.add(leaveRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveRecordllList;
	}

	public Payroll getEmployeeFromCode(Integer code) {

		Payroll employee = payServ.getById(code);

		return employee;
	}

	public long count() {
		return leaveRecordRep.count();
	}

	public List<LeaveRecord> getAll() {
		return (List<LeaveRecord>) leaveRecordRep.findAll();
	}

	public int deleteById(Integer employeeCode) {
		Optional<LeaveRecord> found = leaveRecordRep.findById(employeeCode);
		if (found.isPresent()) {
			leaveRecordRep.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public int updateById(Integer id, LeaveRecord newLeaveRecord) {
		Optional<LeaveRecord> found = leaveRecordRep.findById(id);
		if (found.isPresent()) {
			LeaveRecord temp = found.get();
			temp.setEmployee(newLeaveRecord.getEmployee());
			temp.setOnVacation(newLeaveRecord.getOnVacation());
			temp.setOnSickLeave(newLeaveRecord.getOnSickLeave());
			temp.setDaysWorkedThisMonth(newLeaveRecord.getDaysWorkedThisMonth());
			temp.setSickDays(newLeaveRecord.getSickDays());
			temp.setVacationDays(newLeaveRecord.getVacationDays());
			temp.setVacationStartDate(newLeaveRecord.getVacationStartDate());
			temp.setVacationEndDate(newLeaveRecord.getVacationEndDate());
			temp.setSickLeaveStartDate(newLeaveRecord.getSickLeaveStartDate());
			temp.setSickLeaveEndDate(newLeaveRecord.getSickLeaveEndDate());
			temp.setBonus(newLeaveRecord.getBonus());
			temp.setTransportAllowance(newLeaveRecord.getTransportAllowance());
			leaveRecordRep.save(temp);
			return 0;
		}
		return 1;
	}

	public LeaveRecord getById(Integer employeeCode) {
		Optional<LeaveRecord> found = leaveRecordRep.findById(employeeCode);
		return found.orElse(null);
	}
}
