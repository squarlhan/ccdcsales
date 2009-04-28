package com.dcsh.market.action.zhongzhuanku;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.service.ZhongZhuanKuService;
import com.dcsh.market.Canku;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.ReportCmx;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class SearchDayReportAction implements Preparable {
	private static final Logger log = LogManager.getLogManager().getLogger(SearchDayReportAction.class.getName());
	private ZhongZhuanKuService service;
	private Date mydaterq;
	private List<ReportPmx> reportpmxlist;
	private List<ReportCmx> reportcmxlist;
	
	
	public SearchDayReportAction(ZhongZhuanKuService service)
    {	 
        this.service = service;
	}

	@SuppressWarnings("unchecked")
	public String execute() 
	{
		Canku canku = new Canku();
    	Map session = ActionContext.getContext().getSession();
        canku= ((List<CankuPriv>) session.get("zhongzhuanuser")).get(0).getCanku();   
        
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datestr = null;
        if(this.getMydaterq()!=null){
	        datestr = bartDateFormat.format(this.getMydaterq());
	    }
	    String nowstr =bartDateFormat.format(new Date());	    
		if((mydaterq==null)||datestr.equals(nowstr)){
		this.reportpmxlist = this.service.getDayReportPmx(canku, new Date());
		this.reportcmxlist = this.service.getDayReportCmx(canku, new Date());
		}
		else{
		this.reportpmxlist = this.service.searchDayReportPmx(mydaterq,canku);
		this.reportcmxlist = this.service.searchDayReportCmx(mydaterq,canku);	
			
		}
		
		return "show_report";
	}
	


	public Date getMydaterq() {
		return mydaterq;
	}

	public void setMydaterq(Date mydaterq) {
		this.mydaterq = mydaterq;
	}

	public List<ReportPmx> getReportpmxlist() {
		return reportpmxlist;
	}

	public void setReportpmxlist(List<ReportPmx> reportpmxlist) {
		this.reportpmxlist = reportpmxlist;
	}

	public List<ReportCmx> getReportcmxlist() {
		return reportcmxlist;
	}

	public void setReportcmxlist(List<ReportCmx> reportcmxlist) {
		this.reportcmxlist = reportcmxlist;
	}

	public void prepare() throws Exception {
		
		

	}

}
