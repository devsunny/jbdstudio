package com.asksunny.jbdstudio.rest;

public class InteractiveQuestion {

	String question = null;
	String answer = null;
	String variableName = null;
	
	public InteractiveQuestion(String variableName, String question) {
		super();
		this.variableName = variableName;
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	

}
