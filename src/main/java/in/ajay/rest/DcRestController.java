package in.ajay.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ajay.bindings.DcSummary;
import in.ajay.bindings.Education;
import in.ajay.bindings.Income;
import in.ajay.bindings.Kids;
import in.ajay.bindings.PlanSelection;
import in.ajay.service.DcService;

@RestController
public class DcRestController {

	@Autowired
	private DcService dcService;
	
	@GetMapping("/plan-names")
	public Map<Integer, String> getPlans(){
		return dcService.getPlans();
	}
	
	@PostMapping("/plan-selection")
	public ResponseEntity<String> savePlanSelection(@RequestBody PlanSelection planSelInfo){
		boolean status = dcService.savePlanSelection(planSelInfo);
		if(status) {
			return new ResponseEntity<String>("Plan Selection Saved", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Problem Occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/income")
	public ResponseEntity<String> saveIncomeDetails(@RequestBody Income income){
			boolean status = dcService.saveIncome(income);
			if(status) {
				return new ResponseEntity<String>("Income saved", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Problem Occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/education")
	public ResponseEntity<String> saveEduction(@RequestBody Education education){
		boolean status = dcService.saveEducation(education); 
		 if(status) {
			 return new ResponseEntity<String>("Education saved ", HttpStatus.OK);
		 }
		 	return new ResponseEntity<String>("Problem Occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/kids")
	public ResponseEntity<String> saveKids(@RequestBody Kids kids){
			boolean status = dcService.saveKids(kids);
			if(status) {
				return new ResponseEntity<String>("Kids Saved", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Problem Occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/dc-summary/{caseNum}")
	public ResponseEntity<DcSummary> getDcSummary(@PathVariable Integer caseNum){
		DcSummary summaryInfo = dcService.fetchSummaryInfo(caseNum);
		return new ResponseEntity<DcSummary>(summaryInfo, HttpStatus.OK);
	}
}
