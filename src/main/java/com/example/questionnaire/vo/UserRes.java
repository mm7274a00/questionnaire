package com.example.questionnaire.vo;

import java.util.List;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRes {
	
	@JsonProperty("user")
	private List<User> userList;
	
	private RtnCode rtnCode;

	public UserRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRes(List<User> userList, RtnCode rtnCode) {
		super();
		this.userList = userList;
		this.rtnCode = rtnCode;
	}

	public UserRes(String phoneNumber, RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

}
