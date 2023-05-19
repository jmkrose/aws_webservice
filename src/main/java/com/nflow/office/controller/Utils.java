package com.nflow.office.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
	public List<List<String>> readCsvFile() throws IOException {
		//String sourceFile = "/home/ec2-user/myproject/hostlist.csv";
		String sourceFile = "D://test/hostlist.csv";
		List<List<String>> csvList = new ArrayList<>();
		File csv = new File(sourceFile);
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(csv));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			List<String> aLine = new ArrayList<>();
			String[] lineArr = line.split(",");
			aLine = Arrays.asList(lineArr);
			csvList.add(aLine);
		} 
		return csvList;
	}
		  
	public void writeCsvFile(List<String> resultList) throws IOException {
		LocalDateTime now = LocalDateTime.now();
		//String targetFile = "/home/ec2-user/myproject/telnetResult_" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".csv";
		String targetFile = "D://test/telnetResult_" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".csv";
		File resultCsv = new File(targetFile);
		BufferedWriter bw = null;
		bw = new BufferedWriter(new FileWriter(resultCsv, false));
		for (int i = 0; i < resultList.size(); i++) {
			bw.write(resultList.get(i));
			bw.newLine();
			bw.flush();
		} 
		if (bw != null) { bw.close(); }
	}
}
