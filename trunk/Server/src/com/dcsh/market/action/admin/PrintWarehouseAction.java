package com.dcsh.market.action.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.dcsh.market.Canku;
import com.dcsh.market.Kcxx;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.service.AdminService;
import com.opensymphony.xwork2.Preparable;


public class PrintWarehouseAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(PrintWarehouseAction.class.getName());
    private AdminService service;
    private List<ReportPmx> resultList;
    private Kcxx kcxx;
    private Integer canku;
    private Date date;
    private String printcanku;
    private String printmydate;


	public PrintWarehouseAction(AdminService service) {
    	System.out.println("Enter Constructor");
        this.service = service;
    }

    public Kcxx getKcxx() {
		return kcxx;
	}

	public void setKcxx(Kcxx kcxx) {
		this.kcxx = kcxx;
	}

	public void setresultList(List<ReportPmx> kcxxs) {
		this.resultList = kcxxs;
	}


	public String execute() throws Exception {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(this.date==null)
		{
			this.printmydate = bartDateFormat.format(new Date());
			this.date =  new Date();
		}
		
		else
			this.printmydate = bartDateFormat.format(date);
		
		List<Canku> cankus  = service.getAllCankus();
		
		if(this.canku==0)
			this.printcanku = "È«²¿";
		else
		  for(Canku cankutemp:cankus)
		  {
			if(cankutemp.getId()==this.canku)
				{this.printcanku = cankutemp.getName();break;} 	
		  }
    	this.resultList = service.listStorage(canku,date);
    	
        return "print";
    
    }

     public List<ReportPmx> getresultList() {
        return resultList;
    }

    public Integer getCanku() {
        return canku;
    }

    public void setCanku(Integer canku) {
        this.canku = canku;
    }

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
