package in.ajay.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ajay.entity.IncomeEntity;

public interface IncomeRepo extends JpaRepository<IncomeEntity, Integer>{

}
