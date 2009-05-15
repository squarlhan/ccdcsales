package com.dcsh.market.action.admin;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class adminSaveUserprivAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(getAllGroupsAction.class.getName());
    private AdminService service;
    
    private int userId;
    private List<Boolean> cankupriv;
    private List<Boolean> prdpriv;
    
    public adminSaveUserprivAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    @SuppressWarnings("unchecked")
	public String execute()  throws Exception
    {
    	Map session = ActionContext.getContext().getSession();
    	if(session.containsKey("userId"))
    		this.userId = (Integer)session.get("userId");	
    	service.saveUserpriv(this.cankupriv,this.prdpriv,this.userId);
    	
    	
    	return "save_userpriv";
    }
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
