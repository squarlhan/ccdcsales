package com.dcsh.market.action.zhuchangku;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.XSfahuomx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class getXSfahuoAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getXSfahuoAction.class.getName());
    private ZhuChangKuService service;
    private List<XSfahuomx> xsfhlist;

    public getXSfahuoAction(ZhuChangKuService service) {

        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

    	Map session = ActionContext.getContext().getSession();
        this.xsfhlist = service.getXSfahuomx(((List<CankuPriv>)session.get("zhuchanguser")).get(0).getCanku());

        return "show_report";
      
    }

	public List<XSfahuomx> getXsfhlist() {
		return xsfhlist;
	}

	public void setXsfhlist(List<XSfahuomx> xsfhlist) {
		this.xsfhlist = xsfhlist;
	}

	public ZhuChangKuService getService() {
		return service;
	}

	public void setService(ZhuChangKuService service) {
		this.service = service;
	}

	public void prepare() throws Exception {

    }
}