package com.dcsh.market.action.chuyunchu;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Rkmx;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.Preparable;

public class checkinWareHouseAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(checkinWareHouseAction.class.getName());
    private WareHouseService service;
    private List<Rkmx> resultList;

    public WareHouseService getService() {
		return service;
	}


	public void setService(WareHouseService service) {
		this.service = service;
	}


	public List<Rkmx> getResultList() {
		return resultList;
	}


	public void setResultList(List<Rkmx> resultList) {
		System.out.println("Here");
		System.out.print(
				Thread.currentThread().getStackTrace());
		this.resultList = resultList;
	}


	public checkinWareHouseAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }


	public String execute() {
    	System.out.println("Enter Excute");
    	System.out.println("*******"+this.getResultList());
        service.doCheckProducts(this.getResultList());
        //System.out.println(this.getResultList().get(1).getSaleType());
        return "list";
        //return Action.SUCCESS;
    }

    public void prepare() throws Exception {

    }
}
