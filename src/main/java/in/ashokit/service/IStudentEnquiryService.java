package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.SearchCriteria;
import in.ashokit.entity.StudentEnqEntity;

public interface IStudentEnquiryService {
	public boolean addEnq(StudentEnqEntity se);
	public List<StudentEnqEntity> getEnquiries(Integer counsellorId, SearchCriteria s);
}
