package com.coupang.samples.oop.study.subject.orderManagement.util;

/**
 * Created by andew on 2016. 1. 3..
 */
public enum OrderStatus {

	NONE,
	PROCESS,
	LOGISTICS;

	private static OrderStatus[] allValues = values();
	public static OrderStatus fromOrdinal(int n) {return allValues[n];}
}
