package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.XSfahuomx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class getXSfahuoAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getXSfahuoAction.class.getName());
    private WareHouseService service;
    private List<XSfahuomx> xsfhlist;

    public getXSfahuoAction(WareHouseService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	Map session = ActionContext.getContext().getSession();
    	this.xsfhlist = new ArrayList();
        List<XSfahuomx> tempxsfahuomxs = service.getXSfahuomx(((List<CankuPriv>)session.get("tempuser")).get(0).getCanku());
        for(XSfahuomx xsfahuomx:tempxsfahuomxs){
        	if(xsfahuomx.getXsfahuoxx().getMemo().trim().equals("")||xsfahuomx.getXsfahuoxx().getMemo()==null)
        		xsfahuomx.getXsfahuoxx().setMemo("-");
        	this.xsfhlist.add(xsfahuomx);
        }
        return "show_report";
      
    }

	public List<XSfahuomx> getXsfhlist() {
		return xsfhlist;
	}

	public void setXsfhlist(List<XSfahuomx> xsfhlist) {
		this.xsfhlist = xsfhlist;
	}

	public WareHouseService getService() {
		return service;
	}

	public void setService(WareHouseService service) {
		this.service = service;
	}

	public void prepare() throws Exception {

    }
}