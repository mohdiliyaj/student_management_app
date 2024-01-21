package in.ashokit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.entity.CounsellorEntity;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.StudentEnqRepo;
import in.ashokit.utils.EmailUtil;

@Service
public class CounsellorService implements ICounsellorService {

	private CounsellorRepo counRepo;
	private StudentEnqRepo stuRepo;
	private EmailUtil mailutil;

	public CounsellorService(CounsellorRepo counRepo, EmailUtil mailutil, StudentEnqRepo stuRepo) {
		this.counRepo = counRepo;
		this.mailutil = mailutil;
		this.stuRepo = stuRepo;
	}

	@Override
	public boolean saveCounsellor(CounsellorEntity c) {
		CounsellorEntity byCounsellorEmail = counRepo.findByCounsellorEmail(c.getCounsellorEmail());
		if (byCounsellorEmail != null) {
			return false;
		}
		CounsellorEntity save = counRepo.save(c);
		if (save.getCounsellorId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public CounsellorEntity loginCheck(String email, String password) {
		return counRepo.findByCounsellorEmailAndCounsellorPassword(email, password);
	}

	@Override
	public boolean recoverPassword(String email) {
		CounsellorEntity byCounsellorEmail = counRepo.findByCounsellorEmail(email);
		if (byCounsellorEmail == null) {
			return false;
		}
		String subject = "Password recover - ViganIT";
		String body = "<p> Hi " + byCounsellorEmail.getCounsellorName() + ",</p>" + "<p>Forget password?</p>"
				+ "<p>We received a request to forget password for your account.</p>"
				+ "<p>To login into your account, please use the below password : "
				+ byCounsellorEmail.getCounsellorPassword() + "<br/><br/> <p>Thank you,<br/>ViganIT</p>";

		return mailutil.senEmail(subject, body, email);
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {
		List<StudentEnqEntity> byCounsellorId = stuRepo.findByCounsellorId(counsellorId);
		Integer enrolledStudents = byCounsellorId.stream().filter(e -> "enrolled".equals(e.getEnquiryStatus()))
				.collect(Collectors.toList()).size();
		Integer newStudents = byCounsellorId.stream().filter(e -> "new".equals(e.getEnquiryStatus())).collect(Collectors.toList())
				.size();

		DashboardResponse res = new DashboardResponse();
		res.setTotalEnq(byCounsellorId.size());
		res.setEnrolledEnq(enrolledStudents);
		res.setNewEnq(newStudents);
		res.setLostEnq(byCounsellorId.size() - (enrolledStudents + newStudents));
		return res;
	}
}
