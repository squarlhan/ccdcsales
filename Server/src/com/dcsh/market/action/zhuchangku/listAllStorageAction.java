package com.dcsh.market.action.zhuchangku;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Kcxx;
import com.dcsh.market.KcxxCheck;

import com.dcsh.market.ReportPmx;

import com.dcsh.market.priv.CankuPriv;

import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import java.util.Date;

public class listAllStorageAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
    private ZhuChangKuService service;
    private List<Kcxx> resultList;
    private Kcxx kcxx;
    private Integer canku;
    private List<ReportPmx> pmxListTemp;
    private List<ReportPmx> pmxList;
    private Date mydate;
    private boolean flag;
    
    public listAllStorageAction(ZhuChangKuService service) {
    	System.out.println("Enter Constructor");
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



	public List<Kcxx> getResultList() {
		return resultList;
	}

	public void setResultList(List<Kcxx> resultList) {
		this.resultList = resultList;
	}

	public String execute() {
    	System.out.println("Enter Excute");
    	
            return "list";
        
        //return Action.SUCCESS;
    }

	public String getInfoByDate(){
		System.out.println("Enter Excute");
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhuchanguser");
    	
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    		
    		System.out.println("mydate="+mydate);
    		System.out.println("tempuser: "+user.get(0).getCanku().getId());

  			this.resultList = service.listZhuChangKuAll(user.get(0).getCanku().getId());
  			

    		if (mydate==null){
    			mydate = new Date();
    			this.pmxListTemp = service.getDayReportPmx(user.get(0).getCanku(), mydate);
    		}else{
    			Date nowdate = new Date();
    			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    			String nowdatestr =bartDateFormat.format(nowdate);
    			String datestr =bartDateFormat.format(mydate);
    			if((nowdatestr.equals(datestr))==true){
    				this.pmxListTemp = service.getDayReportPmx(user.get(0).getCanku(), mydate);
    			}else{
    				this.pmxListTemp = service.listZhuChangKuOther(user.get(0).getCanku().getId(), mydate);
    			}
    			
    		}
    		System.out.println("mydate="+mydate);
    		
    		
    		pmxList = new ArrayList<ReportPmx>();
    		for(int i=0;i<pmxListTemp.size();i++){
    				pmxList.add(i,pmxListTemp.get(i));
    		}
            return "infoList";
    	}
	}
	

    public Integer getCanku() {
        return canku;
    }

    public void setCanku(Integer canku) {
        this.canku = canku;
    }
    
    public List<ReportPmx> getPmxList() {
		return pmxList;
	}

	public void setPmxList(List<ReportPmx> pmxList) {
		this.pmxList = pmxList;
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
