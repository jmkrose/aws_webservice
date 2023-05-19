package com.nflow.office.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortCheck {
	  static Utils util = new Utils();
	  
	  @GetMapping("/telnet")
	  public String runPortCheck() {
	    try {
	      List<List<String>> targetList = util.readCsvFile();
	      List<String> resultList = new ArrayList<>();
	      for (int i = 0; i < targetList.size(); i++) {
	        List<String> hostInfo = targetList.get(i);
	        String host = hostInfo.get(0);
	        int portNum = Integer.parseInt(hostInfo.get(1));
	        String result = portCheck(host, portNum);
	        resultList.add(String.valueOf(String.valueOf(host)) + "," + String.valueOf(portNum) + "," + result);
	      } 
	      util.writeCsvFile(resultList);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	    return "Firewall open operation confirmation completed";
	  }
	  
	  public String portCheck(String host, int portNum) throws IOException {
	    Socket s = new Socket();
	    SocketAddress address = new InetSocketAddress(host, portNum);
	    String resultStr = "";
	    try {
	      s.connect(address, 3000);
	      resultStr = "O";
	    } catch (SocketTimeoutException ste) {
	      resultStr = ste.getMessage();
	    } catch (IOException e) {
	      resultStr = e.getMessage();
	    } finally {
	      s.close();
	    } 
	    return resultStr;
	  }
}
