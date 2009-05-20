package com.dcsh.market.action.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Products;
import com.dcsh.market.UserPriv;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class adminShowCFRAction implements Preparable {
	
	private static final Logger log = LogManager.getLogManager().getLogger(getAllGroupsAction.class.getName());
    private AdminService service;
    
    private int cycId;
    private List<UserPriv> userprivs;
    
    private List<Products> products;
    private List<Canku> cankus;
    private List<Boolean> prdpriv;
    private List<Boolean> cankupriv;
    
    public adminShowCFRAction(AdminService service) {
        this.service = service;
    }
    
    @SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	
        this.cankus = service.getAllF();
        this.cankupriv = new ArrayList<Boolean>();
        for(int i=0;i<this.getCankus().size();i++)
        {
        	Boolean bool = service.adjcfr(cycId, this.getCankus().get(i).getId());
            this.getCankupriv().add(bool);
    
        	
        }

    	Map session = ActionContext.getContext().getSession();
    	session.put("cycId", this.cycId);
        return "show";
      
    }
    

	public List<UserPriv> getUserprivs() {
		return userprivs;
	}

	public void setUserprivs(List<UserPriv> userprivs) {
		this.userprivs = userprivs;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public List<Boolean> getPrdpriv() {
		return prdpriv;
	}

	public void setPrdpriv(List<Boolean> prdpriv) {
		this.prdpriv = prdpriv;
	}

	public List<Canku> getCankus() {
		return cankus;
	}

	public void setCankus(List<Canku> cankus) {
		this.cankus = cankus;
	}

	public List<Boolean> getCankupriv() {
		return cankupriv;
	}

	public void setCankupriv(List<Boolean> cankupriv) {
		this.cankupriv = cankupriv;
	}

	public void prepare() throws Exception {
		

	}

	public void setCycId(int cycId) {
		this.cycId = cycId;
	}

	public int getCycId() {
		return cycId;
	}

}