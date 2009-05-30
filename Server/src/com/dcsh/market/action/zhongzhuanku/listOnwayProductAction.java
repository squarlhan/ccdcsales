package com.dcsh.market.action.zhongzhuanku;

import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Chukumx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class listOnwayProductAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listOnwayProductAction.class.getName());
    private ZhongZhuanKuService service;
    private List<Chukumx> resultList;

    public listOnwayProductAction(ZhongZhuanKuService service) {

        this.service = service;
    }

    
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhongzhuanuser");
    	
    	if(user.size()==0){
    		return "input";
    	}
    	else{    	
    		this.resultList = service.listOnwayProducts(user.get(0).getCanku().getId());
            return "listonway";
    	}
    
    }


    public List<Chukumx> getResultList() {
		return resultList;
	}


	public void setResultList(List<Chukumx> resultList) {
		this.resultList = resultList;
	}  



	public void prepare() throws Exception {

    }

}
