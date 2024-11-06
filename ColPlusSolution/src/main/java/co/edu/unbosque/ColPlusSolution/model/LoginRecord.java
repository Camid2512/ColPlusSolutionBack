package co.edu.unbosque.ColPlusSolution.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "login_record")
public class LoginRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Date time;

    @Column(name = "user")
    private String user;

    public LoginRecord() {
    	// TODO Auto-generated constructor stub
    }

    public LoginRecord(Integer recordId, Date date, Date time, String user) {
        this.recordId = recordId;
        this.date = date;
        this.time = time;
        this.user = user;
    }

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
