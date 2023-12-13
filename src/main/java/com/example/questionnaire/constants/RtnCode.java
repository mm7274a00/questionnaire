package com.example.questionnaire.constants;

public enum RtnCode {
	//http static code
	SUCCESSFUL(200,"OK!"), //
	QUESTION_PARAM_ERROR(400,"question param error!"), //
	QUESTIONNAIRE_PARAM_ERROR(400,"questionnaire param error!"), //
	QUESTIONNAIRE_ID_PARAM_ERROR(400,"questionnaire id param error!"), //
	QUESTIONNAIRE_ID_NOT_FOUND(404,"Questionnaire id not found"),//
	UPDATE_ERROR(400,"Update error!"),//
	PHONENUMBER_ERROR1(400,"phoneNumber error!"),//
	PHONENUMBER_ERROR(400,"phoneNumber error!")
	;
			
	private int code;
	
	private String message;

	private RtnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	
}//
