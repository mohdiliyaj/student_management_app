package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentEnqEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	private String studentName;
	private String studentEmail;
	private Long studentPhno;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	private Integer counsellorId;
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public Long getStudentPhno() {
		return studentPhno;
	}
	public void setStudentPhno(Long studentPhno) {
		this.studentPhno = studentPhno;
	}
	public String getClassMode() {
		return classMode;
	}
	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getEnquiryStatus() {
		return enquiryStatus;
	}
	public void setEnquiryStatus(String enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}
	public Integer getCounsellorId() {
		return counsellorId;
	}
	public void setCounsellorId(Integer counsellorId) {
		this.counsellorId = counsellorId;
	}
	@Override
	public String toString() {
		return "StudentEnqEntity [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail="
				+ studentEmail + ", studentPhno=" + studentPhno + ", classMode=" + classMode + ", courseName="
				+ courseName + ", enquiryStatus=" + enquiryStatus + ", counsellorId=" + counsellorId + "]";
	}
}