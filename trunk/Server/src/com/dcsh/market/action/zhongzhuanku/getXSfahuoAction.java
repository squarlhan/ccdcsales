package com.dcsh.market.action.zhongzhuanku;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.XSfahuomx;
import com.dcsh.market.XSyikumx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class getXSfahuoAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getXSfahuoAction.class.getName());
    private ZhongZhuanKuService service;
    private List<XSfahuomx> xsfhlist;

    public getXSfahuoAction(ZhongZhuanKuService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
	public String execute() {
    	System.out.println("Enter Excute");
    	Map session = ActionContext.getContext().getSession();
        this.xsfhlist = service.getXSfahuomx(((List<CankuPriv>)session.get("zhongzhuanuser")).get(0).getCanku());
        System.out.println(xsfhlist);
        return "show_report";
      
    }

	public List<XSfahuomx> getXsfhlist() {
		return xsfhlist;
	}

	public void setXsfhlist(List<XSfahuomx> xsfhlist) {
		this.xsfhlist = xsfhlist;
	}

	public ZhongZhuanKuService getService() {
		return service;
	}

	public void setService(ZhongZhuanKuService service) {
		this.service = service;
	}

	public void prepare() throws Exception {

    }
}