package in.ajay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajay.entity.EducationEntity;

public interface EducationRepo extends JpaRepository<EducationEntity, Long> {

}
