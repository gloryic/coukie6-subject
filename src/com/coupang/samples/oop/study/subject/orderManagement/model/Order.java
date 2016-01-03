package com.coupang.samples.oop.study.subject.orderManagement.model;

import com.coupang.samples.oop.study.subject.orderManagement.util.OrderStatus;

/**
 * Created by andew on 2015. 12. 29..
 */
public class Order {

	private int id;
	private String name;
	private int cost;
	private OrderStatus status;
	private int userId;

	public Order(int id){
		this.id = id;
	}

	public Order(int id, String name, int cost, OrderStatus status, int userId) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.status = status;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString(){
		return id+","+name+","+cost+","+status+","+userId;
	}
}
