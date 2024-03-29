package com.dcsh.market.action.zhuchangku;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.ReportPmx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.ZhuChangKuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class PrintWarehouseAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(PrintWarehouseAction.class.getName());
    private ZhuChangKuService service;
    private Integer canku;
    private List<ReportPmx> pmxListTemp;
    private List<ReportPmx> pmxList;
    private Date mydate;
    private String printcanku;
    private String printmydate;
    public PrintWarehouseAction(ZhuChangKuService service) {
    
        this.service = service;
    }

	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}

	public String execute() throws Exception {   
    	
            return "list";
  
    }
    
	@SuppressWarnings("unchecked")
	public String print() throws Exception
	{
		Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("zhuchanguser");
    	
    	if(user.size()==0){

    		return "input";
    	}
    	else{
    		this.printcanku = user.get(0).getCanku().getName();

    		if (mydate==null){
    			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    			this.printmydate = bartDateFormat.format(new Date());
    			mydate = new Date();
    			this.pmxListTemp = service.getDayReportPmx(user.get(0).getCanku(), mydate);
    		}else{
    			Date nowdate = new Date();
    			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    			this.printmydate = bartDateFormat.format(mydate);
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
            return "print";
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

	public String getPrintcanku() {
		return printcanku;
	}

	public void setPrintcanku(String printcanku) {
		this.printcanku = printcanku;
	}

	public String getPrintmydate() {
		return printmydate;
	}

	public void setPrintmydate(String printmydate) {
		this.printmydate = printmydate;
	}

	public void prepare() throws Exception {

    }
}
