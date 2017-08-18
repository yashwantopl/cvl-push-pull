package com.capitaworld.service.loans.repository.common.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.domain.common.HomeLoanEligibilityCriteria;
import com.capitaworld.service.loans.repository.common.LoanEligibilityCriteriaRepository;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Repository
public class HomeLoanEligibilityCriteriaRepositoryImpl implements LoanEligibilityCriteriaRepository {

	private static final Logger logger = LoggerFactory.getLogger(HomeLoanEligibilityCriteriaRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public HomeLoanEligibilityCriteria getHomeLoanBySalarySlab(Long income, Integer type, Integer bankId) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "getMinMaxFromSalarySlab");
		String query = "select hl from HomeLoanEligibilityCriteria hl where hl.type =:type and hl.bankId =:bankId and hl.isActive =:isActive and "
				+ income + " >= hl.min and " + income + " <= hl.max";
		List<HomeLoanEligibilityCriteria> eligibility = entityManager
				.createQuery(query, HomeLoanEligibilityCriteria.class).setParameter("type", type)
				.setParameter("bankId", bankId).setParameter("isActive", true).getResultList();
		if (!CommonUtils.isListNullOrEmpty(eligibility)) {
			CommonDocumentUtils.endHook(logger, "getMinMaxFromSalarySlab");
			return eligibility.get(0);
		}
		logger.warn("Given Criteria Does not match with the Database Records");
		CommonDocumentUtils.endHook(logger, "getMinMaxFromSalarySlab");
		return null;
	}

	@Override
	public HomeLoanEligibilityCriteria getHomeLoanBySVMV(Long sv, Long mv, Integer bankId) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "getMinMaxFromSalarySlab");
//		hl.type =:type and
		String query = "select hl from HomeLoanEligibilityCriteria hl where hl.bankId =:bankId and hl.isActive =:isActive and ("
				+ sv + " >= hl.minPropertyAmount and " + sv + " <= hl.maxPropertyAmount) and (" + mv
				+ " >= hl.minPropertyAmount and " + mv + " <= hl.maxPropertyAmount)";
		List<HomeLoanEligibilityCriteria> eligibility = entityManager
				.createQuery(query, HomeLoanEligibilityCriteria.class)
				.setParameter("bankId", bankId).setParameter("isActive", true).getResultList();
		if (!CommonUtils.isListNullOrEmpty(eligibility)) {
			CommonDocumentUtils.endHook(logger, "getMinFromSVMV");
			return eligibility.get(0);
		}
		logger.warn("Given Criteria Does not match with the Database Records");
		CommonDocumentUtils.endHook(logger, "getMinFromSVMV");
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

}
