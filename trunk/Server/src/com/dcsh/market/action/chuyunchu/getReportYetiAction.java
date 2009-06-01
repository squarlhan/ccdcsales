package com.dcsh.market.action.chuyunchu;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

public class getReportYetiAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(getReportYetiAction.class.getName());
    private WareHouseService service;
    private List<ReportPmx> reportpmxlist;
    private List<ReportCmx> reportcmxlist;
    private ReportPmx reportpmx;
    private ReportCmx reportcmx;
    private Integer canku;
    private Date mydate;
  
    
 
	public getReportYetiAction(WareHouseService service)
    {
        this.service = service;
	}
    
    @SuppressWarnings("unchecked")
	public String execute() throws Exception {
    	Canku canku = new Canku();
    	Map session = ActionContext.getContext().getSession();
        canku= ((List<CankuPriv>) session.get("tempuser")).get(0).getCanku();     	
    	this.setMydate(new Date());//todo ...
    	this.reportcmxlist = service.getDayReportCmx_yeti(canku, new Date());
        this.reportpmxlist = service.getDayReportPmx_yeti(canku, new Date());
      
    		session.put("reportcmxlist", reportcmxlist);
  
    		session.put("reportpmxlist", reportpmxlist);
 		
        return "list_yeti";
      
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

	public ReportPmx getReportpmx() {
		return reportpmx;
	}

	public void setReportpmx(ReportPmx reportpmx) {
		this.reportpmx = reportpmx;
	}

	public ReportCmx getReportcmx() {
		return reportcmx;
	}

	public void setReportcmx(ReportCmx reportcmx) {
		this.reportcmx = reportcmx;
	}

	public Integer getCanku() {
		return canku;
	}

	public void setCanku(Integer canku) {
		this.canku = canku;
	}

	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}

	public void prepare() throws Exception {
		

	}

}
