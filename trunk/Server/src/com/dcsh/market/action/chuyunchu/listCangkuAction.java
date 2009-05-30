package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.priv.ResourceGrantedAuthorityImpl;
import com.dcsh.market.priv.ResourceType;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.Preparable;

public class listCangkuAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
    private WareHouseService service;
    
    private List<Canku> cangkulist;
	
    public listCangkuAction(WareHouseService service) {

        this.service = service;
    }
    
    
    


	public List<Canku> getCangkulist() {
		return cangkulist;
	}





	public void setCangkulist(List<Canku> cangkulist) {
		this.cangkulist = cangkulist;
	}





	public String execute() throws Exception {

    	PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
	    List<ResourceGrantedAuthorityImpl> list = auth.getGrantedAuthorityResource(ResourceType.CANKU);
    	
    	cangkulist=new ArrayList<Canku>();
    	for(int i=0;i<list.size();i++)
    	{
    		Canku canku =  (Canku)(list.get(i).getResource());
    		if(canku.getType()==(byte)0)cangkulist.add(canku);
    	}
    	
            return "list";
        
      
    }

    
	public void prepare() throws Exception {
	

	}

}
