package com.coupang.samples.oop.study.subject.orderManagement.test;

import com.coupang.samples.oop.study.subject.orderManagement.controller.OrderManager;
import com.coupang.samples.oop.study.subject.orderManagement.dao.OrderDao;
import com.coupang.samples.oop.study.subject.orderManagement.dao.ProductDao;
import com.coupang.samples.oop.study.subject.orderManagement.model.Order;
import com.coupang.samples.oop.study.subject.orderManagement.model.Product;
import com.coupang.samples.oop.study.subject.orderManagement.util.ObjectEqual;
import com.coupang.samples.oop.study.subject.orderManagement.util.OrderStatus;
import com.coupang.samples.oop.study.subject.orderManagement.util.ResizingQueue;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.List;

public class TestMain {
	public static void main(String[] args) {

		//TEST - csv file input, output
		/*
		List<Order> inputList = new ArrayList<>();

		inputList.add(new Order(1,"사과",100, OrderStatus.NONE,0));
		inputList.add(new Order(2,"포도",200, OrderStatus.NONE,0));
		inputList.add(new Order(3,"배추",300, OrderStatus.NONE,0));

		OrderDao.getInstance().insertOrderList(inputList);

		List<Order> list = OrderDao.getInstance().getOrderList();

		for (Order order : list) {
			System.out.println(order.toString());
		}

		//OrderDao.getInstance().deleteAllRows();

		list = OrderDao.getInstance().getOrderList();

		for (Order order : list) {
			System.out.println(order.toString());
		}


		/*
		ResizingQueue<Integer> resizingQueue = new ResizingQueue<>(new ObjectEqual<Integer>() {
			@Override
			public boolean equal(Integer A, Integer B) {
				return A == B;
			}
		});

		resizingQueue.insert(1);
		resizingQueue.insert(2);
		resizingQueue.insert(3);
		resizingQueue.insert(4);
		resizingQueue.insert(5);
		resizingQueue.insert(6);

		resizingQueue.delete(1);
		resizingQueue.delete(2);
		resizingQueue.delete(3);

		Object[] tmp = resizingQueue.getArray();


		for(int i = 0; i < resizingQueue.size(); i++){
			System.out.println(tmp[i]);
		}

		List<Product> productList = ProductDao.getInstance().getProductList();

		for(Product product : productList){
			System.out.println(product.toString());
		}
		*/

//		OrderManager orderManager = OrderManager.getInstance();
//
//		orderManager.order(1);
//
//		orderManager.printOrderList();
//		orderManager.process(1);
//		orderManager.printOrderList();
//		orderManager.logistics(1);


	}
}
