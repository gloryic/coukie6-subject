package com.coupang.samples.oop.study.subject.orderManagement;

import com.coupang.samples.oop.study.subject.orderManagement.controller.OrderManager;
import com.coupang.samples.oop.study.subject.orderManagement.controller.ProductManger;
import com.coupang.samples.oop.study.subject.orderManagement.controller.UserManager;
import com.coupang.samples.oop.study.subject.orderManagement.exception.OrderException;
import com.coupang.samples.oop.study.subject.orderManagement.model.Order;

import java.util.Scanner;

/**
 * Created by andew on 2015. 12. 29..
 *

 1.Queue! (resizing array Queue)
 1.총 갯 수 많아 지면 resizing
 2.총 갯 수 적어 지면 resizing
 3.Head 반 넘으면 resizing

 2.주문 클래스 (상품 ID 넣기)

 3.파일에 주문정보 저장 (JSON 형식)

 4.주문 목록보기 ( 주문 ID, 사람 ID )

 5.물류처리(주문이 처리가 되었는지 아닌지 -> Queue 에서 나갔는지 아직 남아있는지)

 명령어 ex
 1. order :  주문ID, 상품ID (주문을 Q에 넣기)
 2. process : deQ 처리됨 (이 상태에서 파일로 저장됨)
 3. 파일은 cvs 형태(마지막 필드는 process 처리 여부 T/F)
 4. find [order ID] : ID 주문에 대한 상세정보 보여줌
 5. list : 주문 ID 100개 정도 나열
 6. logi [order ID] : process 된 주문을 배송처리
 */

public class Main {
	public static void main(String[] args) {

		int inputInt, productId;
		Scanner inputStr = new Scanner(System.in);
		ProductManger productManger = ProductManger.getInstance();
		OrderManager orderManager = OrderManager.getInstance();
		UserManager userManager = UserManager.getInstance();
		Order order;

		String menu = "주문 관리 프로그램."
			+ "\n\n 명령어 ex)"
			+ "\n 0. 명령어 보기."
			+ "\n 1. 주문 가능한 상품 목록 보기."
			+ "\n 2. 주문한 상품 목록 보기."
			+ "\n 3. 상품 번호로 주문하기."
			+ "\n 4. 상품 번호로 process처리 하기."
			+ "\n 5. 상품 번호로 logisitcs처리 하기."
			+ "\n 6. 상품 번호로 주문 상품 찾기.\n";

		System.out.print(menu+"\n명령어를 입력하세요: ");

		while (inputStr.hasNext()) {
			if(inputStr.hasNextInt()) {
				inputInt = inputStr.nextInt();
				switch (inputInt){
					case 0:
						System.out.println(menu);
						break;

					case 1:
						productManger.printProductList();
						break;

					case 2:
						orderManager.printOrderList();
						break;

					case 3:
						try {
							System.out.print("상품 번호를 입력하세요: ");
							if(inputStr.hasNextInt()) {
								productId = inputStr.nextInt();
								orderManager.order(productId);
							}
						} catch (OrderException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 4:
						try {
							System.out.print("상품 번호를 입력하세요: ");
							if(inputStr.hasNextInt()) {
								productId = inputStr.nextInt();
								orderManager.process(productId);
							}
						} catch (OrderException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 5:
						try {
							System.out.print("상품 번호를 입력하세요: ");
							if(inputStr.hasNextInt()) {
								productId = inputStr.nextInt();
								orderManager.logistics(productId);
							}
						} catch (OrderException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 6:
						System.out.print("상품 번호를 입력하세요: ");
						if(inputStr.hasNextInt()) {
							try {
								productId = inputStr.nextInt();
								order = orderManager.find(productId);
								System.out.println(order.toString());
							} catch (OrderException e) {
								System.out.println(e.getMessage());
							}
						}
						break;

					default:

				}
				System.out.print("\n명령어를 입력하세요: ");
			}
		}
	}
}
