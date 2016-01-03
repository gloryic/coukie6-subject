package com.coupang.samples.oop.study.subject.orderManagement.controller;

/**
 * Created by andew on 2016. 1. 3..
 */
public class UserManager {
	private static volatile UserManager instance;
	private static final Object LOCK = new Object();
	private int userId = 0;
	private String userName = "anonymous";

	private UserManager(){}

	public static UserManager getInstance(){
		if(instance == null){
			synchronized(LOCK) {
				if(instance == null) {
					instance = new UserManager();
				}
			}
		}
		return instance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
