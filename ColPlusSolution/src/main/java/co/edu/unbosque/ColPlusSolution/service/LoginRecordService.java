package co.edu.unbosque.ColPlusSolution.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.ColPlusSolution.model.LoginRecord;
import co.edu.unbosque.ColPlusSolution.repository.LoginRecordRepository;

@Service
public class LoginRecordService {

    @Autowired
    private LoginRecordRepository loginRecRep;

    public LoginRecordService() {
        // Default constructor
    }

    public int create(LoginRecord newRecord) {
        Integer recordId = newRecord.getRecordId();
        Date date = newRecord.getDate();
        Date time = newRecord.getTime();
        String user = newRecord.getUser();

        if (loginRecRep.existsById(recordId)) {
            return 1;
        } else {
            newRecord.setRecordId(recordId);
            newRecord.setDate(date);
            newRecord.setTime(time);
            newRecord.setUser(user);
            loginRecRep.save(newRecord);
            return 0;
        }
    }

    public long count() {
        return loginRecRep.count();
    }

    public List<LoginRecord> getAll() {
        return (List<LoginRecord>) loginRecRep.findAll();
    }

    public int deleteById(Integer recordId) {
        Optional<LoginRecord> found = loginRecRep.findById(recordId);
        if (found.isPresent()) {
            loginRecRep.delete(found.get());
            return 0;
        } else {
            return 1;
        }
    }

    public int updateById(Integer id, LoginRecord newRecord) {
        Optional<LoginRecord> found = loginRecRep.findById(id);
        if (found.isPresent()) {
            LoginRecord temp = found.get();
            temp.setRecordId(newRecord.getRecordId());
            temp.setDate(newRecord.getDate());
            temp.setTime(newRecord.getTime());
            temp.setUser(newRecord.getUser());
            loginRecRep.save(temp);
            return 0;
        }
        return 1;
    }

    public LoginRecord getById(Integer recordId) {
        Optional<LoginRecord> found = loginRecRep.findById(recordId);
        return found.orElse(null);
    }
}
