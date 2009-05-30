package com.dcsh.market.action.zhuchangku;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Kcxx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

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

	public String execute() throws Exception {
    	
            return "list";
        
    }

	@SuppressWarnings("unchecked")
	public String getInfoByDate() throws Exception{

    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhuchanguser");
    	
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{

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
