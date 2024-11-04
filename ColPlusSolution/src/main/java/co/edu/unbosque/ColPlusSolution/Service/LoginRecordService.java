package co.edu.unbosque.ColPlusSolution.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.ColPlusSolution.Model.LoginRecord;
import co.edu.unbosque.ColPlusSolution.Repository.LoginRecordRepository;

@Service
public class LoginRecordService implements CRUDOperation<LoginRecord> {

    @Autowired
    private LoginRecordRepository loginRecordRepository;

    @Override
    public int create(LoginRecord data) {
        loginRecordRepository.save(data);
        return 1;
    }

    @Override
    public List<LoginRecord> getAll() {
        return (List<LoginRecord>) loginRecordRepository.findAll();
    }

    @Override
    public int deleteById(Long id) {
        if (loginRecordRepository.existsById(id)) {
            loginRecordRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateById(Long id, LoginRecord newData) {
        Optional<LoginRecord> record = loginRecordRepository.findById(id);
        if (record.isPresent()) {
            LoginRecord updatedRecord = record.get();
            updatedRecord.setUser(newData.getUser());
            updatedRecord.setDate(newData.getDate());
            updatedRecord.setTime(newData.getTime());
            loginRecordRepository.save(updatedRecord);
            return 1;
        }
        return 0;
    }

    @Override
    public long count() {
        return loginRecordRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return loginRecordRepository.existsById(id);
    }
}
