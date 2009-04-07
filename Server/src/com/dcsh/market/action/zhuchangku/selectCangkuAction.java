package com.dcsh.market.action.zhuchangku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.priv.PrivAuthenticationImpl;
import com.dcsh.market.priv.PrivUtil;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import com.dcsh.market.Canku;;

public class selectCangkuAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
	private ZhuChangKuService service;
    private Integer cangkuId;
    
    public selectCangkuAction(ZhuChangKuService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }
    
	public Integer getCangkuId() {
		return cangkuId;
	}

	public void setCangkuId(Integer cangkuId) {
		this.cangkuId = cangkuId;
	}

	@SuppressWarnings("unchecked")
	public String execute() 
	{
		
		Map session = ActionContext.getContext().getSession();
		Canku cangku = service.getCangkuById(this.getCangkuId());
		
		List<CankuPriv> cankupriv = new ArrayList();		
		PrivAuthenticationImpl auth = (PrivAuthenticationImpl)PrivUtil.getLoginAuthentication();
		
		CankuPriv temp = new CankuPriv(auth.getPrincipal(),cangku);
		System.out.println(cankupriv.size()+"------------------");
		cankupriv.add(temp);
		System.out.println(cankupriv.size()+"------------------");
		session.put("zhuchanguser", cankupriv);
		System.out.println(((List<CankuPriv>) session.get("zhuchanguser")).size()+"------------------");
		return "selectedcangku";
	}
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

}
