package com.dcsh.market.action.xiaoshou;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.XSfahuomx;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.service.XiaoshouService;
import com.opensymphony.xwork2.Preparable;

public class listxsfhAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listxsfhAction.class.getName());
    private XiaoshouService service;
    private List<XSfahuomx> xsfhlist;
    private int id;

    public listxsfhAction(XiaoshouService service) {
        this.service = service;
    }
    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
        this.xsfhlist = service.getXSfahuomx(auth.getPrincipal());
        return "showfh";
      
    }
	public String delete() throws Exception {

		service.delXSfahuomx(id);
        return "delfh";
      
    }

	public List<XSfahuomx> getXsfhlist() {
		return xsfhlist;
	}

	public void setXsfhlist(List<XSfahuomx> xsfhlist) {
		this.xsfhlist = xsfhlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void prepare() throws Exception {

    }
}
