package com.capitaworld.service.loans.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.common.AutoFillOneFormDetailRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.service.common.AutoFillOneFormDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/autofillForm")

public class AutoFillOneFormDetailController {
	@Autowired
	private AutoFillOneFormDetailService autoFillOneFormDetailService;

	@RequestMapping(value = "/getFSDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CorporateApplicantRequest getFSDetails(
			@RequestBody AutoFillOneFormDetailRequest autoFillOneFormDetailRequest, HttpServletRequest request) {
		if (autoFillOneFormDetailRequest != null) {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			System.out.println(autoFillOneFormDetailRequest + "  " + userId);
			
			System.out.println(autoFillOneFormDetailRequest.getFromApplicationId());
			System.out.println(autoFillOneFormDetailRequest.getFromProductId() );
			System.out.println(autoFillOneFormDetailRequest.getToApplicationId());
			System.out.println(autoFillOneFormDetailRequest.getToProductId());
			
		 autoFillOneFormDetailService.getAndSaveCorporateAutoFillOneFormDateils(userId, autoFillOneFormDetailRequest);
		}
		return null;
	}
}
