package com.dcsh.market.action.admin;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class adminSaveCFRAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(getAllGroupsAction.class.getName());
    private AdminService service;
    
    private int cycId;
    public int getCycId() {
		return cycId;
	}
	public void setCycId(int cycId) {
		this.cycId = cycId;
	}
	private List<Boolean> cankupriv;
    private List<Boolean> prdpriv;
    
    public adminSaveCFRAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    @SuppressWarnings("unchecked")
	public String execute()  throws Exception
    {
    	Map session = ActionContext.getContext().getSession();
    	if(session.containsKey("cycId"))
    		this.cycId = (Integer)session.get("cycId");	
    	service.savecfr(this.cankupriv,this.cycId);
    	return "save_cfr";
    }

	public List<Boolean> getCankupriv() {
		return cankupriv;
	}
	public void setCankupriv(List<Boolean> cankupriv) {
		this.cankupriv = cankupriv;
	}
	public List<Boolean> getPrdpriv() {
		return prdpriv;
	}
	public void setPrdpriv(List<Boolean> prdpriv) {
		this.prdpriv = prdpriv;
	}
	public void prepare() throws Exception {
	

	}

}
