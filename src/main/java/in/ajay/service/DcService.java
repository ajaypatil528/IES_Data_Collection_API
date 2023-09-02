package in.ajay.service;

import java.util.Map;

import in.ajay.bindings.DcSummary;
import in.ajay.bindings.Education;
import in.ajay.bindings.Income;
import in.ajay.bindings.Kids;
import in.ajay.bindings.PlanSelection;

public interface DcService {

	public Map<Integer,String> getPlans();
	
	public boolean savePlanSelection(PlanSelection planSel);
	
	public boolean saveIncome(Income income);
	
	public boolean saveEducation(Education education);
	
	public boolean saveKids(Kids kids);
	
	public DcSummary fetchSummaryInfo(Integer caseNum);
}
