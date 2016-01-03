package com.coupang.samples.oop.study.subject.orderManagement.dao;

import com.coupang.samples.oop.study.subject.orderManagement.dbc.CsvManagerImpl;
import com.coupang.samples.oop.study.subject.orderManagement.dbc.ResultSet;
import com.coupang.samples.oop.study.subject.orderManagement.dbc.RowMapper;
import com.coupang.samples.oop.study.subject.orderManagement.model.Order;
import com.coupang.samples.oop.study.subject.orderManagement.util.OrderStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andew on 2015. 12. 29..
 */
public class OrderDao {
	private static volatile OrderDao instance = null;
	public static String FILE_PATH = "/Users/andew/Documents/coupang_cookie/java-oop-samples/src/com/coupang/samples/oop/study/subject/orderManagement/csv/order.csv";
	private static final Object LOCK = new Object();
	private CsvManagerImpl csvManagerImpl;

	private OrderDao(){
		csvManagerImpl = new CsvManagerImpl(FILE_PATH);
		deleteAllRows();
	}

	public static OrderDao getInstance(){
		if(instance == null){
			synchronized(LOCK) {
				if(instance == null) {
					instance = new OrderDao();
				}
			}
		}
		return instance;
	}

	public void insertOrder(Order order){
		ArrayList<Order> tmplist = new ArrayList<>();
		tmplist.add(order);
		csvManagerImpl.insert(tmplist);
	}

	public void insertOrderList(List<Order> orderList){
		csvManagerImpl.insert(orderList);
	}

	public void deleteAllRows(){
		csvManagerImpl.deleteAll();
	}

	public List<Order> getOrderList() {
		return csvManagerImpl.selectAll(new RowMapper<Order>() {
			@Override
			public Order mapRow(ResultSet resultSet, int rowNum) {
				return new Order(resultSet.getInt("id")
					, resultSet.getString("name")
					, resultSet.getInt("cost")
					, OrderStatus.valueOf(resultSet.getString("status"))
					, resultSet.getInt("userId")
				);
			}
		});
	}
}
