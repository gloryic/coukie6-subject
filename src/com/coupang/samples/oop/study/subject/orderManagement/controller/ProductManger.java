package com.coupang.samples.oop.study.subject.orderManagement.controller;

import com.coupang.samples.oop.study.subject.orderManagement.dao.ProductDao;
import com.coupang.samples.oop.study.subject.orderManagement.model.Product;

import java.util.List;

/**
 * Created by andew on 2016. 1. 2..
 */
public class ProductManger {
	private static volatile ProductManger instance;
	private static final Object LOCK = new Object();

	private ProductManger(){}

	public static ProductManger getInstance(){
		if(instance == null){
			synchronized(LOCK) {
				if(instance == null) {
					instance = new ProductManger();
				}
			}
		}
		return instance;
	}

	public List<Product> getProductList(){
		return ProductDao.getInstance().getProductList();
	}

	public Product find(int productId){
		List<Product> productList = getProductList();
		for(Product product : productList){
			if(productId == product.getId())
				return product;
		}
		return null;
	}

	public void printProductList(){
		List<Product> productList = getProductList();
		for(Product product : productList){
			System.out.println(product.toString());
		}
	}
}
