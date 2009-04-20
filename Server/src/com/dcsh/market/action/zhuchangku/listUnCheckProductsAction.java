package com.dcsh.market.action.zhuchangku;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Chukumx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class listUnCheckProductsAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listUnCheckProductsAction.class.getName());
	private ZhuChangKuService service;
	private List<Chukumx> resultList;
	private boolean flag;
    public List<Chukumx> getResultList() {
		return resultList;
	}

	public void setResultList(List<Chukumx> resultList) {
		this.resultList = resultList;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}

	public listUnCheckProductsAction(ZhuChangKuService service) {
		
		super();
		this.service = service;
	}
	
	@SuppressWarnings("unchecked")
	public String execute() {

    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhuchanguser");
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

	public void prepare() throws Exception {

    }
}
