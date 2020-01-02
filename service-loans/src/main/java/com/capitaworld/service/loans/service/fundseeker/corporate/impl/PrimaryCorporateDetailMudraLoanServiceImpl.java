package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetailMudraLoan;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailMudraLoanRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryCorporateDetailMudraLoanService;

public class PrimaryCorporateDetailMudraLoanServiceImpl  implements PrimaryCorporateDetailMudraLoanService{

	@Autowired
	private PrimaryCorporateDetailMudraLoanRepository primaryCorporateDetailMudraLoanRepository; 
	
	@Override
	public void saveUser(FundSeekerInputRequestResponse fundSeekerInputRequestResponse) {
		
		PrimaryCorporateDetailMudraLoan corporateDetailMudraLoan = new PrimaryCorporateDetailMudraLoan();
		
//		corporateDetailMudraLoan.setAvgMonthlySale(fundSeekerInputRequestResponse.getAvgMonthlySale());
//		corporateDetailMudraLoan.setExisting(fundSeekerInputRequestResponse.getExisting());
//		corporateDetailMudraLoan.setMonthlySurplus(fundSeekerInputRequestResponse.getMonthlySurplus());
//		corporateDetailMudraLoan.setMrktArragementFinishedGoods(fundSeekerInputRequestResponse.getMrktArragementFinishedGoods());
//		corporateDetailMudraLoan.setNameOfSupplier(fundSeekerInputRequestResponse);
//		corporateDetailMudraLoan.setOtherExpenses(otherExpenses);
//		corporateDetailMudraLoan.setProposed(proposed);
//		corporateDetailMudraLoan.setPurposeOfMachine(purposeOfMachine);
//		corporateDetailMudraLoan.setRawMaterialsStock(rawMaterialsStock);
//		corporateDetailMudraLoan.setSustenanceOfProprietorPartner(sustenanceOfProprietorPartner);
//		corporateDetailMudraLoan.setTotalCostOfMachine(totalCostOfMachine);
//		corporateDetailMudraLoan.setTypeOfMachine(typeOfMachine);
//		corporateDetailMudraLoan.setWagesSalaries(wagesSalaries);
		
		BeanUtils.copyProperties(fundSeekerInputRequestResponse, corporateDetailMudraLoan);
		
		primaryCorporateDetailMudraLoanRepository.save(corporateDetailMudraLoan);
		
	}

}
