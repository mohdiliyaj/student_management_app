package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.StudentEnqEntity;
import java.util.List;


public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer>{
	public List<StudentEnqEntity> findByCounsellorId(Integer counsellorId);
}
