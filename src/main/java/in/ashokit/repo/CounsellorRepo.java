package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CounsellorEntity;



public interface CounsellorRepo extends JpaRepository<CounsellorEntity, Integer>{
	public CounsellorEntity  findByCounsellorEmail(String counsellorEmail);
	public CounsellorEntity  findByCounsellorEmailAndCounsellorPassword(String counsellorEmail, String counsellorPassword);
}
