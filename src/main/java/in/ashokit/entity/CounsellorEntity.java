package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CounsellorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counsellorId;
	private String counsellorName;
	private String counsellorEmail;
	private String counsellorPassword;
	private String counsellorPhno;
	
	public Integer getCounsellorId() {
		return counsellorId;
	}
	public void setCounsellorId(Integer counsellorId) {
		this.counsellorId = counsellorId;
	}
	public String getCounsellorName() {
		return counsellorName;
	}
	public void setCounsellorName(String counsellorName) {
		this.counsellorName = counsellorName;
	}
	public String getCounsellorEmail() {
		return counsellorEmail;
	}
	public void setCounsellorEmail(String counsellorEmail) {
		this.counsellorEmail = counsellorEmail;
	}
	public String getCounsellorPassword() {
		return counsellorPassword;
	}
	public void setCounsellorPassword(String counsellorPassword) {
		this.counsellorPassword = counsellorPassword;
	}
	public String getCounsellorPhno() {
		return counsellorPhno;
	}
	public void setCounsellorPhno(String counsellorPhno) {
		this.counsellorPhno = counsellorPhno;
	}	
}
