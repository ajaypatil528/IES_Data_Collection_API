package in.ajay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import in.ajay.bindings.DcSummary;
import in.ajay.bindings.Education;
import in.ajay.bindings.Income;
import in.ajay.bindings.Kids;
import in.ajay.bindings.PlanSelection;
import in.ajay.entity.EducationEntity;
import in.ajay.entity.IncomeEntity;
import in.ajay.entity.KidEntity;
import in.ajay.entity.PlanEntity;
import in.ajay.entity.PlanSelEntity;
import in.ajay.repo.EducationRepo;
import in.ajay.repo.IncomeRepo;
import in.ajay.repo.KidRepo;
import in.ajay.repo.PlanRepo;
import in.ajay.repo.PlanSelRepo;
import in.ajay.bindings.DashIncomeResponse;

public class DcServiceImpl implements DcService {

	DashIncomeResponse response = new DashIncomeResponse();
	
	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private PlanSelRepo planSelRepo;
	
	@Autowired
	private IncomeRepo incomeRepo;
	
	@Autowired
	private EducationRepo educationRepo;
	
	@Autowired
	private KidRepo kidRepo;

	@Override
	public Map<Integer,String> getPlans() {
		
		// Get Map object to store plan Id and Name 
		Map<Integer, String> plansMap = new HashMap<>();

		//Get all plans and store in list as entities
		List<PlanEntity> entites = planRepo.findAll();

		//Iterate through all the plans to get plan Id and Name
		for (PlanEntity planEntity : entites) {
			plansMap.put(planEntity.getPlanId(), planEntity.getPlanName());
		}
		
		// return map with plan Id and Name
		return plansMap;
	}

	@Override
	public boolean savePlanSelection(PlanSelection planSel) {

		// Create object of entity class
		
		PlanSelEntity entity = new PlanSelEntity();

		// copy properties of binding class object(planSel) to entity class object
		
		BeanUtils.copyProperties(planSel, entity);

		// save the copied properties of binding class object(planSel) to database
		
		entity = planSelRepo.save(entity);
		
		//return true planId if it(plan record) exists
		return entity.getPlanSelId() > 0;
	}

	@Override
	public boolean saveIncome(Income income) {

		// Create object of entity class
		IncomeEntity entity = new IncomeEntity();

		// copy properties of binding class object(planSel) to entity object class(entity)
		BeanUtils.copyProperties(income, entity);

		// save the copied properties of binding class object(income) to database
		 entity = incomeRepo.save(entity);

		//return true if incomeId (income record) exists
		return entity.getIncomeId() > 0;
	}

	@Override
	public boolean saveEducation(Education education) {
		
		// Create object of entity class
		EducationEntity entity = new EducationEntity();
		
		// copy properties of binding class object(planSel) to entity object class(entity)
		BeanUtils.copyProperties(education, entity);

		// save the copied properties of binding class object(education) to database
		entity = educationRepo.save(entity);
		
		//return true educationId (education record) exists
		return entity.getEducationId() > 0;
	}

	@Override
	public boolean saveKids(Kids kids) {
		
		// Create object of entity class
		KidEntity entity = new KidEntity();
		
		// copy properties of binding class object(planSel) to entity object class(entity)
		BeanUtils.copyProperties(kids, entity);
		
		// save the copied properties of binding class object(kids) to database
		entity = kidRepo.save(entity);
		
		//return true condition if kidId(kids record) exists
		return entity.getKidId() > 0;
	}

	@Override
	public DcSummary fetchSummaryInfo(Integer caseNum) {

		Optional<IncomeEntity> incomeEntity = incomeRepo.findById(caseNum);
		if(incomeEntity.isPresent()) {
			IncomeEntity entity = incomeEntity.get();
			
			Integer incomeCaseNumber = entity.getCaseNum();
			Double salaryIncome = entity.getSalaryIncome();
			Double rentIncome = entity.getRentIncome();
			Double propertyIncome = entity.getPropertyIncome();
			
			response.setCaseNum(incomeCaseNumber);
			response.setSalaryIncome(salaryIncome);
			response.setRentIncome(rentIncome);
			response.setPropertyIncome(propertyIncome);
		}
		return null;
		
		
		
		
		//return new ResponseEntity<T>(response, HttpStatus.OK);
	}

}
