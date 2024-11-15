package co.edu.unbosque.ColPlusSolution.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.edu.unbosque.ColPlusSolution.model.LoginRecord;
import co.edu.unbosque.ColPlusSolution.model.User;
import co.edu.unbosque.ColPlusSolution.repository.LoginRecordRepository;

@Service
public class LoginRecordService {

	private static final String API_URL = "https://api.ipgeolocation.io/ipgeo?apiKey=eb2ff096050a48eea1ab5a9cf55ebcb3";
	@Autowired
	private LoginRecordRepository loginRecRep;

	public LoginRecordService() {
		// Default constructor
	}

	public int create(LoginRecord newRecord) {
		Date date = newRecord.getDate();
		String user = newRecord.getUser();

		if (loginRecRep.existsById(newRecord.getRecordId())) {
			return 1;
		} else {
			newRecord.setDate(date);
			newRecord.setUser(user);
			loginRecRep.save(newRecord);
			return 0;
		}
	}

	public String getPublicIp() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(API_URL, String.class);
	}

	public int createLoginRecord(User userSession) {

		try {
			String jsonResponse = getPublicIp();
			JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
			JsonObject timeZone = jsonObject.getAsJsonObject("time_zone");

			String currentTime = timeZone.get("current_time").getAsString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZZZZZ");

			Date fullDate = dateFormat.parse(currentTime);

			LoginRecord temp = new LoginRecord();
			temp.setUser(userSession.getUser());
			temp.setDate(fullDate);
			loginRecRep.save(temp);
			return 0;
		} catch (ParseException e) {
			e.printStackTrace();
			return 1;
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
