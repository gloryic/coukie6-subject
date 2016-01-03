package com.coupang.samples.oop.study.subject.orderManagement.dbc;

/**
 * Created by andew on 2015. 12. 29..
 */
public interface RowMapper<T> {
	T mapRow(ResultSet resultSet, int rowNum);
}
