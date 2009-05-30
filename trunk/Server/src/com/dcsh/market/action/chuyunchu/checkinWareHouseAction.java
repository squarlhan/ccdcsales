package com.dcsh.market.action.chuyunchu;

import java.util.Iterator;
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
    private int id;

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

		System.out.print(
				Thread.currentThread().getStackTrace());
		this.resultList = resultList;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public checkinWareHouseAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }


	public String execute() throws Exception {
    
    	for (Iterator it = resultList.iterator();it.hasNext();) {
    		Rkmx r = (Rkmx)it.next();
			if ((r.getStatus() == (byte) 1) && (r.getSaleType() == (byte) 0)) {
			
				it.remove();
			
			}
		}
    	if(this.getResultList().size()!=0)service.doCheckProducts(this.getResultList());
   
        return "list";
   
    }
	
	public String delete() throws Exception
	{
		service.deleteUncheckProduct(this.getId());
		
		return "delete_ok";
	}

    public void prepare() throws Exception {

    }
}
