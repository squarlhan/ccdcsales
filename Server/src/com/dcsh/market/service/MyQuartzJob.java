package com.dcsh.market.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.dcsh.market.Canku;
import com.dcsh.market.ReportCmx;
import com.dcsh.market.ReportPmx;

public class MyQuartzJob extends QuartzJobBean {
	
	private WareHouseService mywareHouseService;
	
	public WareHouseService getMywareHouseService() {
		return mywareHouseService;
	}

	public void setMywareHouseService(WareHouseService mywareHouseService) {
		this.mywareHouseService = mywareHouseService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
	//	System.out.println("MyQuartzJob call"+(new Date()));
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
	    String datestr =bartDateFormat.format(new Date());
	   
	    List<Canku> cankus = this.mywareHouseService.getAllCankus();
	    for(Canku mycanku:cankus)
	    {
	    	String bno = String.valueOf(Long.parseLong(datestr)*10000+mycanku.getId()) ;
	    	String bno_yeti = "yeti"+String.valueOf(Long.parseLong(datestr)*10000+mycanku.getId()) ;
	  //  	System.out.println(bno+"bno");
	    	List<ReportCmx> reportcmx = this.mywareHouseService.getDayReportCmx(mycanku, new Date());
	    	List<ReportPmx> reportpmx = this.mywareHouseService.getDayReportPmx(mycanku, new Date());
	    	List<ReportCmx> reportcmx_yeti = this.mywareHouseService.getDayReportCmx_yeti(mycanku, new Date());
	    	List<ReportPmx> reportpmx_yeti = this.mywareHouseService.getDayReportPmx_yeti(mycanku, new Date());
	    	this.mywareHouseService.saveDayReportxx(reportcmx, reportpmx, null, bno, new Date(), mycanku.getId());
	    	this.mywareHouseService.saveDayReportxx_yeti(reportcmx_yeti, reportpmx_yeti, null, bno_yeti, new Date(), mycanku.getId());
	  //  	System.out.println(mycanku.getName()+"'s report saved");
	    }
	}

}
