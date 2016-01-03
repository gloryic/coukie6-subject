package com.coupang.samples.oop.study.subject.orderManagement.dbc;

import java.util.List;

/**
 * Created by andew on 2016. 1. 2..
 */
public interface CsvManager {
	<T> List<T> selectAll(RowMapper<T> rowMapper);
	<T> void insert(List<T> objectList);
	void deleteAll();
}
