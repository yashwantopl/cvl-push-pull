package com.capitaworld.service.loans.repository.common.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.domain.common.HomeLoanEligibilityCriteria;
import com.capitaworld.service.loans.domain.common.PersonalLoanEligibilityCriteria;
import com.capitaworld.service.loans.repository.common.LoanEligibilityCriteriaRepository;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Repository
public class LoanEligibilityCriteriaRepositoryImpl implements LoanEligibilityCriteriaRepository {

	private static final Logger logger = LoggerFactory.getLogger(LoanEligibilityCriteriaRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	//HomeLoan Starts
	@Override
	public HomeLoanEligibilityCriteria getHomeLoanBySalarySlab(Long income, Integer type, Integer bankId) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "getHomeLoanBySalarySlab");
		String query = "select hl from HomeLoanEligibilityCriteria hl where hl.type =:type and hl.bankId =:bankId and hl.isActive =:isActive and "
				+ income + " >= hl.min and " + income + " <= hl.max";
		List<HomeLoanEligibilityCriteria> eligibility = entityManager
				.createQuery(query, HomeLoanEligibilityCriteria.class).setParameter("type", type)
				.setParameter("bankId", bankId).setParameter("isActive", true).getResultList();
		if (!CommonUtils.isListNullOrEmpty(eligibility)) {
			CommonDocumentUtils.endHook(logger, "getHomeLoanBySalarySlab");
			return eligibility.get(0);
		}
		logger.warn("Given Criteria Does not match with the Database Records");
		CommonDocumentUtils.endHook(logger, "getHomeLoanBySalarySlab");
		return null;
	}

	@Override
	public HomeLoanEligibilityCriteria getHomeLoanBySVMV(Long sv, Long mv, Integer bankId) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "getHomeLoanBySVMV");
//		hl.type =:type and
		String query = "select hl from HomeLoanEligibilityCriteria hl where hl.bankId =:bankId and hl.isActive =:isActive and ("
				+ sv + " >= hl.minPropertyAmount and " + sv + " <= hl.maxPropertyAmount) and (" + mv
				+ " >= hl.minPropertyAmount and " + mv + " <= hl.maxPropertyAmount)";
		List<HomeLoanEligibilityCriteria> eligibility = entityManager
				.createQuery(query, HomeLoanEligibilityCriteria.class)
				.setParameter("bankId", bankId).setParameter("isActive", true).getResultList();
		if (!CommonUtils.isListNullOrEmpty(eligibility)) {
			CommonDocumentUtils.endHook(logger, "getHomeLoanBySVMV");
			return eligibility.get(0);
		}
		logger.warn("Given Criteria Does not match with the Database Records");
		CommonDocumentUtils.endHook(logger, "getHomeLoanBySVMV");
		return null;
	}

	@Override
	public Object[] getMinMaxRoiForHomeLoan() {
		@SuppressWarnings("unchecked")
		List<Object[]> data = entityManager.createQuery("select min(hl.roiLow),max(hl.roiHigh) from HomeLoanEligibilityCriteria hl where hl.isActive =:isActive")
				.setParameter("isActive", true).getResultList();
		if (!CommonUtils.isListNullOrEmpty(data)) {
			return data.get(0);
		}
		return null;
	}
    //Home Loans Ends
	
	//Personal Loan Starts
	@Override
	public PersonalLoanEligibilityCriteria getPersonalLoanBySalarySlab(Long income, Integer type, Integer bankId) {
		CommonDocumentUtils.startHook(logger, "getPersonalLoanBySalarySlab");
		String query = "select pl from PersonalLoanEligibilityCriteria pl where pl.type =:type and pl.bankId =:bankId and pl.isActive =:isActive and "
				+ income + " >= pl.min and " + income + " <= pl.max";
		List<PersonalLoanEligibilityCriteria> eligibility = entityManager
				.createQuery(query, PersonalLoanEligibilityCriteria.class).setParameter("type", type)
				.setParameter("bankId", bankId).setParameter("isActive", true).getResultList();
		if (!CommonUtils.isListNullOrEmpty(eligibility)) {
			CommonDocumentUtils.endHook(logger, "getPersonalLoanBySalarySlab");
			return eligibility.get(0);
		}
		logger.warn("Given Criteria Does not match with the Database Records");
		CommonDocumentUtils.endHook(logger, "getPersonalLoanBySalarySlab");
		return null;
	}
	
	@Override
	public Object[] getMinMaxRoiForPersonalLoan(Integer type) {
		@SuppressWarnings("unchecked")
		List<Object[]> data = entityManager.createQuery("select min(pl.roiLow),max(pl.roiHigh) from PersonalLoanEligibilityCriteria pl where pl.isActive =:isActive and pl.type =:type")
				.setParameter("isActive", true)
				.setParameter("type", type)
				.getResultList();
		
		if (!CommonUtils.isListNullOrEmpty(data)) {
			return data.get(0);
		}
		return null;
	}
	
	//Personal Loan Ends
	
}
