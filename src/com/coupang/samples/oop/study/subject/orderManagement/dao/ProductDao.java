package com.coupang.samples.oop.study.subject.orderManagement.dao;

import com.coupang.samples.oop.study.subject.orderManagement.dbc.CsvManagerImpl;
import com.coupang.samples.oop.study.subject.orderManagement.dbc.ResultSet;
import com.coupang.samples.oop.study.subject.orderManagement.dbc.RowMapper;
import com.coupang.samples.oop.study.subject.orderManagement.model.Order;
import com.coupang.samples.oop.study.subject.orderManagement.model.Product;

import java.util.List;

/**
 * Created by andew on 2015. 12. 29..
 */
public class ProductDao {
	private static volatile ProductDao instance = null;
	public static String FILE_PATH = "/Users/andew/Documents/coupang_cookie/java-oop-samples/src/com/coupang/samples/oop/study/subject/orderManagement/csv/product.csv";
	private static final Object LOCK = new Object();
	private CsvManagerImpl csvManagerImpl;

	private ProductDao(){
		csvManagerImpl = new CsvManagerImpl(FILE_PATH);
	}

	public static ProductDao getInstance(){
		if(instance == null){
			synchronized(LOCK) {
				if(instance == null) {
					instance = new ProductDao();
				}
			}
		}
		return instance;
	}

	public void setProductList(List<Product> orderList){
		csvManagerImpl.insert(orderList);
	}

	public List<Product> getProductList(){
		return csvManagerImpl.selectAll(new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet resultSet, int rowNum) {
				return new Product(resultSet.getInt("id")
					,resultSet.getString("name")
					,resultSet.getInt("cost")
				);
			}
		});
	}

	public void deleteAllRows(){
		csvManagerImpl.deleteAll();
	}

}
