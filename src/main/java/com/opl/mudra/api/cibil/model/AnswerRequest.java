
package com.opl.mudra.api.cibil.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerRequest {

	private String questionKey;
	private List<String> answerKey;
	private String userInputAnswer;
	private Boolean resendOTP;
	private Boolean skipQuestion;

	public AnswerRequest() {
		super();
	}

	public String getQuestionKey() {
		return questionKey;
	}

	public void setQuestionKey(String questionKey) {
		this.questionKey = questionKey;
	}

	public List<String> getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(List<String> answerKey) {
		this.answerKey = answerKey;
	}

	public String getUserInputAnswer() {
		return userInputAnswer;
	}

	public void setUserInputAnswer(String userInputAnswer) {
		this.userInputAnswer = userInputAnswer;
	}

	public Boolean getResendOTP() {
		return resendOTP;
	}

	public void setResendOTP(Boolean resendOTP) {
		this.resendOTP = resendOTP;
	}

	public Boolean getSkipQuestion() {
		return skipQuestion;
	}

	public void setSkipQuestion(Boolean skipQuestion) {
		this.skipQuestion = skipQuestion;
	}

}
