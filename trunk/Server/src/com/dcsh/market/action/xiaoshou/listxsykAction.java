package com.dcsh.market.action.xiaoshou;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.XSyikumx;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.service.XiaoshouService;
import com.opensymphony.xwork2.Preparable;

public class listxsykAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listxsykAction.class.getName());
    private XiaoshouService service;
    private List<XSyikumx> xsyklist;
    private int id;

    public listxsykAction(XiaoshouService service) {
        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
        this.xsyklist = service.getXSyikumx(auth.getPrincipal());
        return "showyk";
      
    }
	public String delete() throws Exception {

		service.delXSyikumx(id);
        return "delyk";
      
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public List<XSyikumx> getXsyklist() {
		return xsyklist;
	}

	public void setXsyklist(List<XSyikumx> xsyklist) {
		this.xsyklist = xsyklist;
	}

	public void prepare() throws Exception {

    }
}
