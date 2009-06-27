package com.dcsh.market.action.zhongzhuanku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.XSyikumx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class getXSyikuAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(getXSyikuAction.class.getName());
    private ZhongZhuanKuService service;
    private List<XSyikumx> xsyklist;

    public getXSyikuAction(ZhongZhuanKuService service) {
    
        this.service = service;
    }
    
	public String execute() throws Exception {
    
    	Map session = ActionContext.getContext().getSession();
    	this.xsyklist  = new ArrayList();
        List<XSyikumx> tempxsyikumxs = service.getXSyikumx(((List<CankuPriv>)session.get("zhongzhuanuser")).get(0).getCanku());
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

	public ZhongZhuanKuService getService() {
		return service;
	}

	public void setService(ZhongZhuanKuService service) {
		this.service = service;
	}

	public void prepare() throws Exception {

    }
}