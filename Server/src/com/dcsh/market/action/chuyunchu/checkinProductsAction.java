package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Chukumx;
import com.dcsh.market.Rkxx;
import com.dcsh.market.Users;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class checkinProductsAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(checkinProductsAction.class.getName());
    private WareHouseService service;
    private List<Chukumx> resultList;
    private String bno;
    private int rkfzr;
    private boolean flag;
    private List<Boolean> checked;
    
    
    
    
    public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public int getRkfzr() {
		return rkfzr;
	}

	public void setRkfzr(int rkfzr) {
		this.rkfzr = rkfzr;
	}

	public List<Chukumx> getResultList() {
		return resultList;
	}

	public void setResultList(List<Chukumx> resultList) {
		this.resultList = resultList;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<Boolean> getChecked() {
		return checked;
	}

	public void setChecked(List<Boolean> checked) {
		this.checked = checked;
	}

	public boolean isFlag() {
		return flag;
	}

	public checkinProductsAction(WareHouseService service) {
		super();
		System.out.println("Enter Constructor");
		this.service = service;
	}

    @SuppressWarnings("unchecked")
	public String execute() {
    	System.out.println("Enter Excute");
    	System.out.println("*******"+this.getResultList());
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    	    Rkxx rkxx = new Rkxx(user.get(0).getCanku(),user.get(0).getUser(),new Users(this.getRkfzr()),this.getBno().trim(),new Date());
    	    
    	    List<Chukumx> chukumxs = new ArrayList<Chukumx>();
    	    for(int i=0;i<this.getResultList().size();i++)
    	    {
    	    	if(this.getChecked().get(i))
    	    		chukumxs.add(this.getResultList().get(i));
    	    	System.out.println(this.getChecked()+"--------------wl");
    	    }
    	    
    	    
            service.doentryChuYunchu(chukumxs,rkxx);
            return "list";
        }
    }

	public void prepare() throws Exception {

    }
}