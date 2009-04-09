package com.dcsh.market.action.ajaxaction;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.dcsh.market.Custom;
import com.dcsh.market.service.XiaoshouService;
import com.dcsh.market.service.XiaoshouServiceImpl;
import com.opensymphony.xwork2.Action;

public class GetCustomersAction implements Action{
	
	private List<Custom> customers;
	private List<String[]> names;
	private List<Custom> allcustomers;
	private String start;
    private XiaoshouService service;
	
    public GetCustomersAction(XiaoshouService service)
    {
	   System.out.println("Enter Constructor");
        this.service = service;
	}
 
	public List<Custom> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Custom> customers) {
		this.customers = customers;
	}	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}

	public List<String[]> getNames() {
		return names;
	}

	public void setNames(List<String[]> names) {
		this.names = names;
	}

	public List<Custom> getAllcustomers() {
		return allcustomers;
	}

	public void setAllcustomers(List<Custom> allcustomers) {
		this.allcustomers = allcustomers;
	}

	public String execute() {
		System.out.println("Enter Execute");
		customers = new ArrayList();
		names = new ArrayList();
		allcustomers = this.service.getAllCustom();
		if(start == null || "".equals(start.trim())) {
            start = "a";
            System.out.println("ssssss£º"+start);
        }
		for(Custom c:allcustomers){
			if(c.getCustomName().toLowerCase().startsWith(start.toLowerCase())) {
				customers.add(c);
				names.add(new String[]{c.getCustomName(),String.valueOf(c.getId())});
				//names.add(new String[]{new String(c.getCustomName().getBytes("iso8859-1"),"gb2312"),String.valueOf(c.getId())});
            }
		}
		for(Custom c:customers){
			System.out.println("kkkkkkkkk£º"+c.getCustomName());
		}
		
		return "show";
	}

}
