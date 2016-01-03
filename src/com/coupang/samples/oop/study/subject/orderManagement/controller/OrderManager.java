package com.coupang.samples.oop.study.subject.orderManagement.controller;

import com.coupang.samples.oop.study.subject.orderManagement.dao.OrderDao;
import com.coupang.samples.oop.study.subject.orderManagement.exception.OrderException;
import com.coupang.samples.oop.study.subject.orderManagement.model.Order;
import com.coupang.samples.oop.study.subject.orderManagement.model.Product;
import com.coupang.samples.oop.study.subject.orderManagement.util.ObjectEqual;
import com.coupang.samples.oop.study.subject.orderManagement.util.OrderStatus;
import com.coupang.samples.oop.study.subject.orderManagement.util.ResizingQueue;

import java.util.List;

/**
 * Created by andew on 2016. 1. 2..
 */
public class OrderManager {
	private static volatile OrderManager instance;
	private static final Object LOCK = new Object();
	private ResizingQueue<Order> resizingQueue;
	private OrderDao orderDao;
	private ProductManger productManger;

	private OrderManager(){
		orderDao = OrderDao.getInstance();
		productManger = ProductManger.getInstance();
		resizingQueue = new ResizingQueue<>(new ObjectEqual<Order>() {
			@Override
			public boolean equal(Order A, Order B) {
				if(A != null && B != null)
					return A.getId() == B.getId();
				else
					return false;
			}
		});
	}

	public static OrderManager getInstance(){
		if(instance == null){
			synchronized(LOCK) {
				if(instance == null) {
					instance = new OrderManager();
				}
			}
		}
		return instance;
	}

	public void process(int productId) throws OrderException {
		Order findOrder = new Order(productId);
		Order order = resizingQueue.find(findOrder);

		if(order != null) {
			order.setStatus(OrderStatus.PROCESS);
			orderDao.insertOrder(order);
			resizingQueue.delete(findOrder);
		}
		else
			throw new OrderException("주문되지 않은 상품입니다.");
	}

	public void order(int productId) throws OrderException {
		Product product = productManger.find(productId);

		if(product != null){
			int userId = UserManager.getInstance().getUserId();
			Order order = new Order(product.getId(), product.getName(), product.getCost(), OrderStatus.NONE, userId);
			resizingQueue.insert(order);
		}
		else
			throw new OrderException("유효하지 않은 상품입니다.");
	}

	public Order find(int productId) throws OrderException {
		Order order = resizingQueue.find(new Order(productId));
		if(order != null)
			return order;
		else
			throw new OrderException("주문 상품이 없습니다.");
	}

	public Object[] getOrderlist(){
		return resizingQueue.getArray();
	}

	public void logistics(int productId) throws OrderException {
		boolean isExist = false;
		List<Order> orderList = orderDao.getOrderList();

		for(Order order : orderList){
			if(order.getId() == productId){
				order.setStatus(OrderStatus.LOGISTICS);
				isExist = true;
			}
		}

		if(isExist) {
			orderDao.deleteAllRows();
			orderDao.insertOrderList(orderList);
		}
		else throw new OrderException("유효하지 않은 상품입니다.");
	}

	public void printOrderList(){
		Object[] orders = resizingQueue.getArray();
		for(int i = 0; i < resizingQueue.size(); i++){
			System.out.println(orders[i]);
		}
	}
}