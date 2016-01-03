package com.coupang.samples.oop.study.subject.orderManagement.dbc;

import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Created by andew on 2015. 12. 29..
 */
public class CsvManagerImpl implements CsvManager {
	private static final Object LOCK = new Object();
	private String filePath;
	private String cvsSplitBy = ",";

	public CsvManagerImpl(String filePath){
		this.filePath = filePath;
		File file = new File(this.filePath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public <T> List<T> selectAll(RowMapper<T> rowMapper){
		List<T> list = new ArrayList<>();
		List<ResultSet> resultSets = new ArrayList<>();
		List<String[]> results;
		Map<String, Integer> keys;

		ResultReadCsv resultReadCsv = getObjectFromCSV();

		if(resultReadCsv == null){
			return list;
		}
		else{
			results = resultReadCsv.getResults();
			keys = resultReadCsv.getKeys();

			list = new ArrayList<>();

			for(String[] row : results){
				resultSets.add(new ResultSet(row, keys));
			}

			for(int i = 0; i < results.size(); i++){
				list.add(rowMapper.mapRow(resultSets.get(i), i));
			}
			return list;
		}
	}

	@Override
	public synchronized <T> void insert(List<T> objectList){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
			for(T object : objectList) {
				out.write(object.toString());
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void deleteAll(){
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(filePath));

			if((line = br.readLine()) != null){
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
				out.write(line);
				out.newLine();
				out.close();
			}

			if (br != null)
				br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ResultReadCsv getObjectFromCSV(){
		ResultReadCsv resultReadCsv = new ResultReadCsv();
		ArrayList<String[]> rows = new ArrayList<>();
		Map<String, Integer> keys = new HashMap<>();
		BufferedReader br = null;
		String line;
		String[] row, columnNames;

		try {
			br = new BufferedReader(new FileReader(filePath));

			if((line = br.readLine()) != null){
				columnNames = line.split(cvsSplitBy);
				for(int i = 0; i < columnNames.length; i++){
					keys.put(columnNames[i], i);
				}
				while ((line = br.readLine()) != null) {
					row = line.split(cvsSplitBy);
					rows.add(row);
				}
			}
			resultReadCsv.setResults(rows);
			resultReadCsv.setKeys(keys);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultReadCsv;
	}
}
