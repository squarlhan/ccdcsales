package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.XSyikumx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class getXSyikuAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getXSyikuAction.class.getName());
    private WareHouseService service;
    private List<XSyikumx> xsyklist;

    public getXSyikuAction(WareHouseService service) {
        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

    	Map session = ActionContext.getContext().getSession();
    	this.xsyklist = new ArrayList();
        List<XSyikumx> tempxsyikumxs = service.getXSyikumx(((List<CankuPriv>)session.get("tempuser")).get(0).getCanku());
        for(XSyikumx xsyikumx:tempxsyikumxs){
        	if(xsyikumx.getXsyikuxx().getMemo().trim().equals("")||xsyikumx.getXsyikuxx().getMemo()==null)
        		xsyikumx.getXsyikuxx().setMemo("-");
        	this.xsyklist.add(xsyikumx);
        }
        return "show_report";
      
    }

	public List<XSyikumx> getXsyklist() {
		return xsyklist;
	}

	public void setXsyklist(List<XSyikumx> xsyklist) {
		this.xsyklist = xsyklist;
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