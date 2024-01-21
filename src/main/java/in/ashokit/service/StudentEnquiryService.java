package in.ashokit.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import in.ashokit.binding.SearchCriteria;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.repo.StudentEnqRepo;

@Service
public class StudentEnquiryService implements IStudentEnquiryService {
	private StudentEnqRepo stuRepo;
	public StudentEnquiryService(StudentEnqRepo stuRepo) {
		this.stuRepo = stuRepo;
	}

	@Override
	public boolean addEnq(StudentEnqEntity se) {
		StudentEnqEntity save = stuRepo.save(se);
		if(save.getStudentId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<StudentEnqEntity> getEnquiries(Integer counsellorId, SearchCriteria s) {
		StudentEnqEntity enq = new StudentEnqEntity();
		enq.setCounsellorId(counsellorId);
		
		if(s.getClassMode() !=null && s.getClassMode()!="") {
			enq.setClassMode(s.getClassMode());
		}
		if(s.getCourseName()!=null && s.getCourseName()!="") {
			enq.setCourseName(s.getCourseName());
		}
		if(s.getEnquiryStatus()!=null && s.getEnquiryStatus()!="") {
			enq.setEnquiryStatus(s.getEnquiryStatus());
		}
		Example<StudentEnqEntity> of = Example.of(enq);
		List<StudentEnqEntity> all = stuRepo.findAll(of);
		return all;
	}

}
