package com.opl.mudra.api.cibil.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyAnswerRequest implements Serializable {

	private static final long serialVersionUID = 760231437412707949L;

	private List<AnswerRequest> answers;
	private String challengeConfigGUID;
	private CibilRequest cibilRequest;
	private String queueName;
	private Boolean lastChanceQuestion;

	public VerifyAnswerRequest() {
		super();
		answers = Collections.emptyList();
	}

	public List<AnswerRequest> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerRequest> answers) {
		this.answers = answers;
	}

	public String getChallengeConfigGUID() {
		return challengeConfigGUID;
	}

	public void setChallengeConfigGUID(String challengeConfigGUID) {
		this.challengeConfigGUID = challengeConfigGUID;
	}

	public CibilRequest getCibilRequest() {
		return cibilRequest;
	}

	public void setCibilRequest(CibilRequest cibilRequest) {
		this.cibilRequest = cibilRequest;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public Boolean getLastChanceQuestion() {
		return lastChanceQuestion;
	}

	public void setLastChanceQuestion(Boolean lastChanceQuestion) {
		this.lastChanceQuestion = lastChanceQuestion;
	}
	

}
