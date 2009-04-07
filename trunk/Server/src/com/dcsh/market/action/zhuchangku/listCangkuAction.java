package com.dcsh.market.action.zhuchangku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.dcsh.market.Canku;

public class listCangkuAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
    private ZhuChangKuService service;
    
    private List<Canku> cangkulist;
	
    public listCangkuAction(ZhuChangKuService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
    
    


	public List<Canku> getCangkulist() {
		return cangkulist;
	}





	public void setCangkulist(List<Canku> cangkulist) {
		this.cangkulist = cangkulist;
	}





	public String execute() {
    	System.out.println("Enter Excute");
    	PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	    List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.CANKU);
    	
    	cangkulist=new ArrayList<Canku>();
    	for(int i=0;i<list.size();i++)
    	{
    		Canku canku =  (Canku)(list.get(i).getResource());
    		if(canku.getType()==(byte)1)cangkulist.add(canku);
    	}
    	
            return "list";
        
      
    }

    
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

}
