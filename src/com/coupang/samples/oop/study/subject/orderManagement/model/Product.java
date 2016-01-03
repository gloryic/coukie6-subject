package com.coupang.samples.oop.study.subject.orderManagement.model;

/**
 * Created by andew on 2015. 12. 29..
 */
public class Product {

	private int id;
	private String name;
	private int cost;

	public Product(int id, String name, int cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
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

	@Override
	public String toString(){
		return id+","+name+","+cost;
	}
}
