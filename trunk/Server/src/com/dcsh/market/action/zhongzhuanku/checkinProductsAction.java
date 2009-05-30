package com.dcsh.market.action.zhongzhuanku;

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
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class checkinProductsAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(checkinProductsAction.class.getName());
    private ZhongZhuanKuService service;
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

	public List<Boolean> getChecked() {
		return checked;
	}

	public void setChecked(List<Boolean> checked) {
		this.checked = checked;
	}

	public checkinProductsAction(ZhongZhuanKuService service) {
		super();
		this.service = service;
	}

    public String execute() throws Exception {

    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhongzhuanuser");
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    	    Rkxx rkxx = new Rkxx(user.get(0).getCanku(),user.get(0).getUser(),new Users(this.getRkfzr()),this.getBno().trim(),new Date());
           
    	    List<Chukumx> chukumxs = new ArrayList<Chukumx>();
    	    for(int i=0;i<this.getChecked().size();i++)
    	    {
    	    	if(this.getChecked().get(i)!=null)
    	    		chukumxs.add(this.getResultList().get(i));
    	 
    	    }
    	    
    	    
    	    service.doentryZhongzhuanku(chukumxs,rkxx);
            return "list";
        }
    }

	public void prepare() throws Exception {

    }

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}
}
