package in.ajay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajay.entity.KidEntity;

public interface KidRepo extends JpaRepository<KidEntity, Integer> {

}
