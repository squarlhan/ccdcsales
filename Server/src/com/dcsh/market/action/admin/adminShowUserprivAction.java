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

public class adminShowUserprivAction implements Preparable {
	
	private static final Logger log = LogManager.getLogManager().getLogger(getAllGroupsAction.class.getName());
    private AdminService service;
    
    private int userId;
    private List<UserPriv> userprivs;
    
    private List<Products> products;
    private List<Canku> cankus;
    private List<Boolean> prdpriv;
    private List<Boolean> cankupriv;
    
    public adminShowUserprivAction(AdminService service) {
        this.service = service;
    }
    
    @SuppressWarnings("unchecked")
	public String execute() {
    	
        this.cankus = service.getAllCankus();
        this.products = service.getAllProducts();    
        this.userprivs = service.getuserprivByUserId(this.getUserId());
        
        this.cankupriv = new ArrayList<Boolean>();
        this.prdpriv = new ArrayList<Boolean>();
        for(int i=0;i<this.getCankus().size();i++)
        {
        	Boolean bool = true;
        
        	for(UserPriv tempuserpriv:this.getUserprivs())
        	{
        		String string = tempuserpriv.getResource();
        		
        		if(string.contains("canku")&&Integer.parseInt(string.substring(string.indexOf(":")+1))==this.getCankus().get(i).getId())
        		{
        			this.getCankupriv().add(true);
        			System.out.println("true");
        			bool = false;
        			break;
        		}
        		      		
        	}
        	if(bool)
        	{       		
    			  this.getCankupriv().add(false);
    	     	  System.out.println("false");
        	}
        	
        }
        for(int j=0;j<this.getProducts().size();j++)
        {
        	Boolean bool = true;
        	
        	for(UserPriv tempuserpriv:this.getUserprivs())
        	{
        		String string = tempuserpriv.getResource();
        		
        		if(string.contains("prd")&&Integer.parseInt(string.substring(string.indexOf(":")+1))==this.getProducts().get(j).getId())
        		{
        			this.getPrdpriv().add(true);
        			System.out.println("true");
        			bool=false;
        			break;
        		}       		
        	}
        	if(bool)
        	{
        		this.getPrdpriv().add(false);
  	     	    System.out.println("false");
        	}
        
        }
    	Map session = ActionContext.getContext().getSession();
    	session.put("userId", this.userId);
        return "show";
      
    }
    
    
    
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

}
