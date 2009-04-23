package com.dcsh.market.action.chuyunchu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.dcsh.market.Canku;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.ReportCmx;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class SearchDayReportAction implements Preparable {
	private static final Logger log = LogManager.getLogManager().getLogger(SearchDayReportAction.class.getName());
	private WareHouseService service;
	private Date mydate;
	private List<ReportPmx> reportpmxlist;
	private List<ReportCmx> reportcmxlist;
	
	
	public SearchDayReportAction(WareHouseService service)
    {
	 
        this.service = service;
	}

	@SuppressWarnings("unchecked")
	public String execute() 
	{
		Canku canku = new Canku();
    	Map session = ActionContext.getContext().getSession();
        canku= ((List<CankuPriv>) session.get("tempuser")).get(0).getCanku();   
        
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String datestr =bartDateFormat.format(mydate);
	    String nowstr =bartDateFormat.format(new Date());
	    System.out.println(datestr+"***"+nowstr);//dddd
	    
		if(mydate.equals(null)||datestr.equals(nowstr)){
			System.out.println("today++++++++++++++++++");
		this.reportpmxlist = this.service.getDayReportPmx(canku, new Date());
		this.reportcmxlist = this.service.getDayReportCmx(canku, new Date());
		}
		else{
			System.out.println(mydate+"++++++++++++++++++");
		this.reportpmxlist = this.service.searchDayReportPmx(mydate,canku);
		this.reportcmxlist = this.service.searchDayReportCmx(mydate,canku);	
			
		}
		
		return "show_report";
	}
	
	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
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
