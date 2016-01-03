package com.coupang.samples.oop.study.subject.orderManagement.dbc;

import java.util.List;
import java.util.Map;

/**
 * Created by andew on 2015. 12. 29..
 */
public class ResultSet {

	private String[] row;
	private Map<String, Integer> keys;

	public ResultSet(String[] row, Map<String, Integer> keys){
		this.row = row;
		this.keys = keys;
	}

	public int getInt(String key){
		return Integer.parseInt(row[(keys.get(key))]);
	}

	public String getString(String key){
		//TODO value가 null일 경우 예외 처리
		return row[(keys.get(key))];
	}

	public int getIntByColumnIndex(int index){
		return Integer.parseInt(row[index]);
	}

	public String getStringByColumnIndex(int index){
		return row[index];
	}

}
