package com.dcsh.market.action.chuyunchu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.*;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class saveReportAction  implements Preparable {

	private static final Logger log = LogManager.getLogManager().getLogger(saveReportAction.class.getName());
	private String bno;
	private Date mydate;
    private WareHouseService service;
	
    public saveReportAction(WareHouseService service)
    {
	   System.out.println("Enter Constructor");
        this.service = service;
	}
    public String execute() 
    {
    	List<ReportCmx> reportcmxlist =  new ArrayList<ReportCmx>();
    	List<ReportPmx> reportpmxlist = new ArrayList<ReportPmx>();
    	Users users = new Users();
    	Canku canku= new Canku();
    	int cankuid=0 ;
        Map session = ActionContext.getContext().getSession();
       
      	if(session.containsKey("reportcmxlist"))
      	{
      		reportcmxlist = (List<ReportCmx>) session.get("reportcmxlist");
      		
      	}
      	if(session.containsKey("reportpmxlist"))
        {
      		reportpmxlist = (List<ReportPmx>) session.get("reportpmxlist");
        }
      	if(session.containsKey("tempuser"))
      	{
      		List<CankuPriv> test=(List<CankuPriv>) session.get("tempuser");
      		users= test.get(0).getUser();
      		
      		canku= ((List<CankuPriv>) session.get("tempuser")).get(0).getCanku();
      		cankuid= canku.getId();
      	}
      	
      	
      	System.out.println(reportcmxlist+"reportcmxlist");
    	System.out.println(reportpmxlist+"reportpmxlist");
    	System.out.println(users+"users");
    	System.out.println(canku.getId()+"getjjjjj");
    	System.out.println(this.getBno());
  
      	service.saveDayReportxx(reportcmxlist, reportpmxlist,users, bno.trim(), new Date(),cankuid);
    	return "save_ok";
    }
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
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
