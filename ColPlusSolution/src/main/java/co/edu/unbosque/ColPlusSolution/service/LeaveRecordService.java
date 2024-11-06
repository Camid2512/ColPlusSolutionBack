package co.edu.unbosque.ColPlusSolution.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.ColPlusSolution.model.LeaveRecord;
import co.edu.unbosque.ColPlusSolution.model.Payroll;
import co.edu.unbosque.ColPlusSolution.repository.LeaveRecordRepository;

@Service
public class LeaveRecordService {

    @Autowired
    private LeaveRecordRepository leaveRecordRep;

    public LeaveRecordService() {
        // Default constructor
    }

    public int create(LeaveRecord newLeaveRecord) {
        Integer employeeCode = newLeaveRecord.getEmployeeCode();
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

        if (leaveRecordRep.existsById(employeeCode)) {
            return 1;
        } else {
            newLeaveRecord.setEmployeeCode(employeeCode);
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
