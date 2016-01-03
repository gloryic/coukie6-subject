package com.coupang.samples.oop.study.subject.orderManagement.util;

/**
 * Created by andew on 2015. 12. 29..
 */
public class ResizingQueue<T> {
	private T[] array;
	private int tail = 2;
	private int head = 0;
	private ObjectEqual<T> objectEqual;

	public ResizingQueue(ObjectEqual<T> objectEqual){
		this.objectEqual = objectEqual;
		array = (T[]) new Object[tail];
	}

	private void arrayResizingDouble(){
		T[] tmpArray = (T[]) new Object[tail * 2];
		for(int i = 0; i < head; i++){
			tmpArray[i] = array[i];
		}
		array = tmpArray;
		tail *= 2;
	}

	private void arrayResizingHalf() {
		T[] tmpArray = (T[]) new Object[tail/2];
		for(int i = 0; i < head; i++){
			tmpArray[i] = array[i];
		}
		array = tmpArray;
		tail /= 2;
	}

	private int findIndex(T object){
		for(int i = 0; i < tail; i++){
			if(objectEqual.equal(array[i], object))
				return i;
		}
		return -1;
	}

	public T find(T object){
		for(int i = 0; i < tail; i++){
			if(objectEqual.equal(array[i], object))
				return array[i];
		}
		return null;
	}

	public synchronized void insert(T object){
		if(head + 1 == tail)
			arrayResizingDouble();
		array[head++] = object;
	}

	public synchronized void delete(T object){
		int deleteIndex = findIndex(object);
		for(int i = deleteIndex; i < head; i++){
			array[i] = array[i+1];
		}
		--head;
		if(head + 1 <= tail/2)
			arrayResizingHalf();
	}

	public synchronized void update(T object){
		int updateIndex = findIndex(object);
		array[updateIndex] = object;
	}

	public int size(){
		return head;
	}

	public T[] getArray(){
		return array;
	}


}
