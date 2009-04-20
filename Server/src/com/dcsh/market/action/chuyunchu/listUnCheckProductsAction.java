package com.dcsh.market.action.chuyunchu;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Rkmx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class listUnCheckProductsAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listUnCheckProductsAction.class.getName());
    private WareHouseService service;
    private List<Rkmx> resultList;
    private Rkmx rkmx;
    private boolean flag;
    
    public listUnCheckProductsAction(WareHouseService service) {
        this.service = service;
    }

    
	@SuppressWarnings("unchecked")
	public String execute() {

    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    		
    		this.resultList = service.listUnCheckProducts(user.get(0).getCanku().getId());
    		if(this.resultList.size()==0)
    			flag=true;
    		else
    			flag=false;
            return "welcome";
    	}
     
    }


    public List<Rkmx> getResultList() {
		return resultList;
	}


	public void setResultList(List<Rkmx> resultList) {
		this.resultList = resultList;
	}  


	public Rkmx getRkmx() {
		return rkmx;
	}


	public void setRkmx(Rkmx rkmx) {
		this.rkmx = rkmx;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public boolean isFlag() {
		return flag;
	}


	public void prepare() throws Exception {

    }
}