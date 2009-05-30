package com.dcsh.market.action.zhongzhuanku;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.CheckInTable;
import com.dcsh.market.Kcxx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class checkinAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
    private ZhongZhuanKuService service;
    private List<CheckInTable> resultList;
    private Kcxx kcxx;
    private Integer canku;
    private Date mydate;
    private boolean flag;
    
    public checkinAction(ZhongZhuanKuService service) {

        this.service = service;
    }

    public Kcxx getKcxx() {
		return kcxx;
	}

	public void setKcxx(Kcxx kcxx) {
		this.kcxx = kcxx;
	}
	
	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}

	public List<CheckInTable> getResultList() {
		return resultList;
	}

	public void setResultList(List<CheckInTable> resultList) {
		this.resultList = resultList;
	}

	public String execute()  throws Exception{
    	
        return "list";

    }

	public String getInfoByDate() throws Exception{
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhongzhuanuser");
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    		if(mydate==null)
    			mydate = new Date();
    		this.resultList = service.getCheckInTable(user.get(0).getCanku(), mydate);
    		
            return "infoList";
    	}
	}
	

    public Integer getCanku() {
        return canku;
    }

    public void setCanku(Integer canku) {
        this.canku = canku;
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
