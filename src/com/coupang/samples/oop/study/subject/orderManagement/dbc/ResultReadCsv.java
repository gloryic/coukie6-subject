package com.coupang.samples.oop.study.subject.orderManagement.dbc;

import java.util.List;
import java.util.Map;

/**
 * Created by andew on 2016. 1. 3..
 */
public class ResultReadCsv {
	List<String[]> results;
	Map<String, Integer> keys;

	public void setResults(List<String[]> results) {
		this.results = results;
	}

	public void setKeys(Map<String, Integer> keys) {
		this.keys = keys;
	}

	public List<String[]> getResults() {
		return results;
	}

	public Map<String, Integer> getKeys() {
		return keys;
	}
}