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

public class SearchDayReportYetiAction implements Preparable {
	private static final Logger log = LogManager.getLogManager().getLogger(SearchDayReportYetiAction.class.getName());
	private WareHouseService service;
	private Date begindate;
	private Date enddate;
	private List<ReportPmx> reportpmxlist;
	private List<ReportCmx> reportcmxlist;
	
	
	public SearchDayReportYetiAction(WareHouseService service)
    {
	 
        this.service = service;
	}

	@SuppressWarnings("unchecked")
	public String execute()  throws Exception
	{
		Canku canku = new Canku();
    	Map session = ActionContext.getContext().getSession();
        canku= ((List<CankuPriv>) session.get("tempuser")).get(0).getCanku();   
        
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String begindatestr = null;
        String enddatestr = null;
        if(this.getBegindate()!=null){
        	begindatestr = bartDateFormat.format(this.getBegindate());
	    }
        if(this.getEnddate()!=null){
        	enddatestr = bartDateFormat.format(this.getEnddate());
	    }
	    String nowstr =bartDateFormat.format(new Date());
	    
		if (((this.getBegindate() == null) || begindatestr.equals(nowstr))
				&& (this.getEnddate() == null || enddatestr.equals(nowstr))) {
			this.reportpmxlist = this.service.getDayReportPmx_yeti(canku, new Date());
			this.reportcmxlist = this.service.getDayReportCmx_yeti(canku, new Date());
		} else if (((this.getBegindate() == null) || begindatestr
				.equals(nowstr))
				&& this.getEnddate().after(new Date())) {
			this.reportpmxlist = this.service.getDayReportPmx_yeti(canku, new Date());
			this.reportcmxlist = this.service.getDayReportCmx_yeti(canku, new Date());
		} else if ((this.getEnddate() == null || enddatestr.equals(nowstr) || this
				.getEnddate().after(new Date()))
				&& this.getBegindate().before(new Date())) {
			this.reportpmxlist = this.service.searchDayReportPmx_yeti(this.getBegindate(), new Date(), canku);
			this.reportcmxlist = this.service.searchDayReportCmx_yeti(this.getBegindate(), new Date(), canku);
		} else if (this.getBegindate().before(this.getEnddate())
				&& this.getEnddate().before(new Date())) {
			this.reportpmxlist = this.service.searchDayReportPmx_yeti(this.getBegindate(), this.getEnddate(), canku);
			this.reportcmxlist = this.service.searchDayReportCmx_yeti(this.getBegindate(), this.getEnddate(), canku);
		} else {
			this.reportpmxlist = null;
			this.reportcmxlist = null;
		}
		
		return "show_report_yeti";
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
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
