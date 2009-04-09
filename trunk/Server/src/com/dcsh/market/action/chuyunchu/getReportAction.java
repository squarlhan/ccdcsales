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

public class getReportAction implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(getReportAction.class.getName());
    private WareHouseService service;
    private List<ReportPmx> reportpmxlist;
    private List<ReportCmx> reportcmxlist;
    private ReportPmx reportpmx;
    private ReportCmx reportcmx;
    private Integer canku;
    private Date mydate;
  
    
 
	public getReportAction(WareHouseService service)
    {
	   System.out.println("Enter Constructor");
        this.service = service;
	}
    
    public String execute() {
    	Canku canku = new Canku();
    	Map session = ActionContext.getContext().getSession();
        canku= ((List<CankuPriv>) session.get("tempuser")).get(0).getCanku();
      	
        System.out.println(canku.getId()+"jjjjjjj");
    	System.out.println("Enter Excute");
    	this.setMydate(new Date());//todo ...
    	System.out.println(this.getCanku()+"����"+this.getMydate());
    	this.reportcmxlist = service.getDayReportCmx(canku, new Date());
        this.reportpmxlist = service.getDayReportPmx(canku, new Date());
      
    		session.put("reportcmxlist", reportcmxlist);
  
    		session.put("reportpmxlist", reportpmxlist);
 		
        System.out.println(reportpmxlist);
        System.out.println(reportcmxlist+"nihao");
        return "list";
      
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