package com.capitaworld.service.loans.repository.common.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.domain.common.HomeLoanEligibilityCriteria;
import com.capitaworld.service.loans.domain.common.LAPEligibilityCriteria;
import com.capitaworld.service.loans.domain.common.PersonalLoanEligibilityCriteria;
import com.capitaworld.service.loans.repository.common.LoanEligibilityCriteriaRepository;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Repository
public class LoanEligibilityCriteriaRepositoryImpl implements LoanEligibilityCriteriaRepository {

	private static final Logger logger = LoggerFactory.getLogger(LoanEligibilityCriteriaRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	// HomeLoan Starts
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
		// hl.type =:type and
		String query = "select hl from HomeLoanEligibilityCriteria hl where hl.bankId =:bankId and hl.isActive =:isActive and ("
				+ sv + " >= hl.minPropertyAmount and " + sv + " <= hl.maxPropertyAmount) and (" + mv
				+ " >= hl.minPropertyAmount and " + mv + " <= hl.maxPropertyAmount)";
		List<HomeLoanEligibilityCriteria> eligibility = entityManager
				.createQuery(query, HomeLoanEligibilityCriteria.class).setParameter("bankId", bankId)
				.setParameter("isActive", true).getResultList();
		if (!CommonUtils.isListNullOrEmpty(eligibility)) {
			CommonDocumentUtils.endHook(logger, "getHomeLoanBySVMV");
			return eligibility.get(0);
		}
		logger.warn("Given Criteria Does not match with the Database Records");
		CommonDocumentUtils.endHook(logger, "getHomeLoanBySVMV");
		return null;
	}

	@Override
	public Object[] getMinMaxRoiForHomeLoan(List<Integer> bankIds) {
		if (CommonUtils.isListNullOrEmpty(bankIds)) {
			return null;
		}

		@SuppressWarnings("unchecked")
		List<Object[]> data = entityManager.createQuery(
				"select min(hl.roiLow),max(hl.roiHigh) from HomeLoanEligibilityCriteria hl where hl.isActive =:isActive and hl.bankId in (:ids)")
				.setParameter("isActive", true).setParameter("ids", bankIds).getResultList();
		if (!CommonUtils.isListNullOrEmpty(data)) {
			return data.get(0);
		}
		return null;
	}
	// Home Loans Ends

	// Personal Loan Starts
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

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getMinMaxRoiForPersonalLoan(List<Integer> bankIds, Integer type) {
		if (CommonUtils.isListNullOrEmpty(bankIds)) {
			return null;
		}
		
		List<Object[]> data = entityManager.createQuery(
				"select min(pl.roiLow),max(pl.roiHigh) from PersonalLoanEligibilityCriteria pl where pl.isActive =:isActive and pl.type =:type and pl.bankId in (:ids)")
				.setParameter("isActive", true)
				.setParameter("type", type)
				.setParameter("ids", bankIds)
				.getResultList();

		if (!CommonUtils.isListNullOrEmpty(data)) {
			return data.get(0);
		}
		return null;
	}

	// Personal Loan Ends

	// LAP Starts
	@Override
	public LAPEligibilityCriteria getLAPBySalarySlab(Long income, Integer type, Integer bankId, Integer propertyType) {
		CommonDocumentUtils.startHook(logger, "getLAPBySalarySlab");
		String query = "select lap from LAPEligibilityCriteria lap where lap.type =:type and lap.bankId =:bankId and lap.isActive =:isActive and lap.propertyType =:propertyType and "
				+ income + " >= lap.min";
		List<LAPEligibilityCriteria> eligibility = entityManager.createQuery(query, LAPEligibilityCriteria.class)
				.setParameter("type", type).setParameter("bankId", bankId).setParameter("propertyType", propertyType)
				.setParameter("isActive", true).getResultList();
		if (!CommonUtils.isListNullOrEmpty(eligibility)) {
			CommonDocumentUtils.endHook(logger, "getLAPBySalarySlab");
			return eligibility.get(0);
		}
		logger.warn("Given Criteria Does not match with the Database Records");
		CommonDocumentUtils.endHook(logger, "getLAPBySalarySlab");
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getMinMaxRoiForLAP(List<Integer> bankIds, Integer employementType, Integer propertyType) {
		if (CommonUtils.isListNullOrEmpty(bankIds)) {
			logger.warn("Bank Ids got Null or Empty");
			return null;
		}
		List<Object[]> data = entityManager.createQuery(
				"select min(lap.roiLow),max(lap.roiHigh) from LAPEligibilityCriteria lap where lap.isActive =:isActive and lap.bankId in (:ids) and lap.type =:employementType and lap.propertyType =:propertyType")
				.setParameter("isActive", true).setParameter("ids", bankIds).setParameter("propertyType", propertyType)
				.setParameter("employementType", employementType).getResultList();
		if (!CommonUtils.isListNullOrEmpty(data)) {
			return data.get(0);
		}
		return null;
	}

	// LAP Ends

}
