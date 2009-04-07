package com.dcsh.market.action.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dcsh.market.Canku;
import com.dcsh.market.Cankumanage;
import com.dcsh.market.Products;
import com.dcsh.market.Specifications;
import com.dcsh.market.action.chuyunchu.listAllStorageAction;
import com.dcsh.market.service.AdminService;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.Preparable;

public class admincanmanagerAction implements Preparable{
	private List<Canku> resultListTemp;
	private List<Cankumanage> resultList;
	private Canku canku;
	private AdminService service;
	private String newcname;
	private byte newctype;
	private String newcaddr;

	private String id;
	private String name;
	private String address;
	private String type;



	private static final Logger log = LogManager.getLogManager().getLogger(productsManagerAction.class.getName());
	public List<Cankumanage> getResultList() {
		return resultList;
	}
	public void setResultList(List<Cankumanage> resultList) {
		this.resultList = resultList;
	}
	
	
	
	public String getNewcname() {
		return newcname;
	}
	public void setNewcname(String newcname) {
		this.newcname = newcname;
	}
	public byte getNewctype() {
		return newctype;
	}
	public void setNewctype(byte newctype) {
		this.newctype = newctype;
	}
	public String getNewcaddr() {
		return newcaddr;
	}
	
	public void setNewcaddr(String newcaddr) {
		this.newcaddr = newcaddr;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public admincanmanagerAction(AdminService service) {
	    	System.out.println("Enter Constructor");
	        this.service = service;
	    }
	@SuppressWarnings("null")
	public String execute() {
		byte type;
		int id;
		String name = null;
		String address = null;
		String typeName = null;
		this.resultList = new ArrayList<Cankumanage>();
    	System.out.println("Enter Excute");
        this.resultListTemp = service.getAllCankus();
        System.out.println("resultListTemp.size()="+resultListTemp.size());
        for(int i=0;i<resultListTemp.size();i++){
        	Cankumanage result = new Cankumanage();
        	type=resultListTemp.get(i).getType();
        	if(type==0)
        		typeName="储运处";
        	else
        		if(type==1)
        			typeName="驻厂库";
        		else
        			if(type==2)
        				typeName="中转库";
        			else
        				typeName="未知";
        	address = resultListTemp.get(i).getAddress();
        	result.setAddress(address);
        	id=resultListTemp.get(i).getId();
        	result.setId(id);
        	name=resultListTemp.get(i).getName();
        	result.setName(name);
        	result.setType(type);
        	result.setTypeName(typeName);
        	resultList.add(i, result);
        System.out.println("getName"+i+"="+resultList.get(i).getName()+" getAddress"+i+"="+resultList.get(i).getAddress()+" getType"+i+"="+resultList.get(i).getType()+"typeName"+i+"="+typeName);
        }
        for(int i=0;i<resultList.size();i++){
        	System.out.println("getName"+i+"="+resultList.get(i).getName()+" getAddress"+i+"="+resultList.get(i).getAddress()+" getType"+i+"="+resultList.get(i).getType()+"typeName"+i+"="+typeName);
        }
        return "list";
        //return Action.SUCCESS;
    }
	public String modify(){
		

	//	HttpServletRequest request = ServletActionContext.getRequest();

		System.out.println("modify");


	//	String id = request.getParameter("id");
	//	String name = request.getParameter("name");
	//	String type = request.getParameter("type");
	//	String address = request.getParameter("address");
		System.out.println(this.getId().trim()+new String(this.getName().trim()));
		System.out.println("type "+this.getType().trim());
		System.out.println("name "+this.getName().trim());

		canku = new Canku();

		canku.setId(Integer.valueOf(this.getId().trim()));
		canku.setName(this.getName().trim());
		canku.setType((byte)Integer.parseInt(this.getType().trim()));
		canku.setAddress(this.getAddress().trim());

	    service.updateCanku(canku);
		return "modify";
	}
	public String delete(){
	//	HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("delete");
	//	String id = request.getParameter("id");
		System.out.println(this.getId().trim());
		canku = new Canku();
		canku.setId(Integer.valueOf(this.getId().trim()));
		service.delCanku(canku);
		return "delete";
	}
	public String add(){
		System.out.println("add!!"+newcname);
		canku = new Canku();
		canku.setName(newcname.trim());
		canku.setAddress(newcaddr.trim());
		canku.setType(newctype);
		System.out.println("newctype="+newctype);
		service.addCanku(canku);
		return "add";
	}
	
	public void prepare() throws Exception {

    }
}
