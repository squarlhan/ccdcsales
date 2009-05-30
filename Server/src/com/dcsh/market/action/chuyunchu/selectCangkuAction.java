package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class selectCangkuAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
	private WareHouseService service;
    private Integer cangkuId;
    
    public selectCangkuAction(WareHouseService service) {
    
        this.service = service;
    }
    
	public Integer getCangkuId() {
		return cangkuId;
	}

	public void setCangkuId(Integer cangkuId) {
		this.cangkuId = cangkuId;
	}

	@SuppressWarnings("unchecked")
	public String execute()  throws Exception
	{
		
		Map session = ActionContext.getContext().getSession();
		Canku cangku = service.getCangkuById(this.getCangkuId());
		
		List<CankuPriv> cankupriv = new ArrayList();		
		PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
		
		CankuPriv temp = new CankuPriv(auth.getPrincipal(),cangku);

		cankupriv.add(temp);
	
		session.put("tempuser", cankupriv);
	
		return "selectedcangku";
	}
	public void prepare() throws Exception {
	

	}

}
