package com.dcsh.market.action.admin;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.UserGroup;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;

public class getAllGroupsAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getAllGroupsAction.class.getName());
    private AdminService service;
    private List<UserGroup> resultList;
    
	public List<UserGroup> getResultList() {
		return resultList;
	}

	public void setResultList(List<UserGroup> resultList) {
		this.resultList = resultList;
	}

	public getAllGroupsAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
	
	public String execute() {
    	System.out.println("Enter Excute");
        this.resultList = service.getAllGroups();
//        switch(tempList.size()%4){
//        case 0:if(tempList.size()!=0){
//        	for(int i = 0; i<=tempList.size()-4;i+=4){
//        	resultList.add(new FourGroups(tempList.get(i),tempList.get(i+1),tempList.get(i+2),tempList.get(i+3)));
//        }}break;
//        case 1:if(tempList.size()==1){
//        	resultList.add(new FourGroups(tempList.get(0)));
//        }else{
//        	for(int i = 0; i<=tempList.size()-5;i+=4){
//            	resultList.add(new FourGroups(tempList.get(i),tempList.get(i+1),tempList.get(i+2),tempList.get(i+3)));
//            	}resultList.add(new FourGroups(tempList.get(tempList.size()-1)));	
//        }break;
//        case 2:if(tempList.size()==2){
//        	resultList.add(new FourGroups(tempList.get(0),tempList.get(1)));
//        }else{
//        	for(int i = 0; i<=tempList.size()-5;i+=4){
//            	resultList.add(new FourGroups(tempList.get(i),tempList.get(i+1),tempList.get(i+2),tempList.get(i+3)));
//            	}resultList.add(new FourGroups(tempList.get(tempList.size()-1),tempList.get(tempList.size()-2)));	
//        }break;
//        case 3:if(tempList.size()==1){
//        	resultList.add(new FourGroups(tempList.get(0),tempList.get(1),tempList.get(2)));
//        }else{
//        	for(int i = 0; i<=tempList.size()-5;i+=4){
//            	resultList.add(new FourGroups(tempList.get(i),tempList.get(i+1),tempList.get(i+2),tempList.get(i+3)));
//            	}resultList.add(new FourGroups(tempList.get(tempList.size()-1),tempList.get(tempList.size()-2),tempList.get(tempList.size()-3)));	
//        }break;
//        }
        
        System.out.println(resultList);
        return "show";
      
    }
    
    public void prepare() throws Exception {

    }

}
