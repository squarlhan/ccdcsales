package com.dcsh.market.action.chuyunchu;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.dcsh.market.Kcxx;
import com.dcsh.market.KcxxCheck;
import com.dcsh.market.ReportPmx;
import com.dcsh.market.priv.CankuPriv;
import com.dcsh.market.service.WareHouseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;
import java.util.Date;

public class listAllStorageAction implements Preparable{
	private static final Logger log = LogManager.getLogManager().getLogger(listAllStorageAction.class.getName());
    private WareHouseService service;
    private List<Kcxx> resultListTemp;
    private List<KcxxCheck> resultList;
    private Kcxx kcxx;
    private Integer canku;
    private List<ReportPmx> pmxListTemp;
    private List<ReportPmx> pmxList;
    private Date mydate;
    private boolean flag;
    private String printcanku;
    private String printmydate;
    public listAllStorageAction(WareHouseService service) {
    
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



	public List<KcxxCheck> getResultList() {
		return resultList;
	}

	public void setResultList(List<KcxxCheck> resultList) {
		this.resultList = resultList;
	}

	public String execute() throws Exception {   
    	
            return "list";
  
    }

	public String getInfoByDate() throws Exception{
	
    	Map session = ActionContext.getContext().getSession();
    	List<CankuPriv> user = (List<CankuPriv>)session.get("tempuser");
    	resultList = new ArrayList<KcxxCheck>();
    	if(user.size()==0){
    		this.setFlag(true);
    		return "input";
    	}
    	else{
    		this.resultListTemp = service.listWarehouse(user.get(0).getCanku().getId());
    		for(int i=0;i<resultListTemp.size();i++){
    			KcxxCheck result = new KcxxCheck();
    			switch(resultListTemp.get(i).getSaleType())
    			{
    				
    				case 0: result.setSaleTypeName("");
    				result.setId(resultListTemp.get(i).getId());
    				result.setProducts(resultListTemp.get(i).getProducts());
    				result.setSpecifications(resultListTemp.get(i).getSpecifications());
    				result.setMemo(resultListTemp.get(i).getMemo());
    				result.setNumber(resultListTemp.get(i).getNumber());
    				switch(resultListTemp.get(i).getStatus()){
    				case 0:result.setStatusName("待检");break;
    				case 1:result.setStatusName("合格");break;
    				case 2:result.setStatusName("不合格");break;
    				}
    				resultList.add(i,result);
    				break;
    				case 1: result.setSaleTypeName("内销");
    				result.setId(resultListTemp.get(i).getId());
    				result.setProducts(resultListTemp.get(i).getProducts());
    				result.setSpecifications(resultListTemp.get(i).getSpecifications());
    				result.setMemo(resultListTemp.get(i).getMemo());
    				result.setNumber(resultListTemp.get(i).getNumber());
    				switch(resultListTemp.get(i).getStatus()){
    				case 0:result.setStatusName("待检");break;
    				case 1:result.setStatusName("合格");break;
    				case 2:result.setStatusName("不合格");break;
    				}
    				resultList.add(i,result);break;
    				case 2: result.setSaleTypeName("定向");
    				result.setId(resultListTemp.get(i).getId());
    				result.setProducts(resultListTemp.get(i).getProducts());
    				result.setSpecifications(resultListTemp.get(i).getSpecifications());
    				result.setMemo(resultListTemp.get(i).getMemo());
    				result.setNumber(resultListTemp.get(i).getNumber());
    				switch(resultListTemp.get(i).getStatus()){
    				case 0:result.setStatusName("待检");break;
    				case 1:result.setStatusName("合格");break;
    				case 2:result.setStatusName("不合格");break;
    				}
    				resultList.add(i,result);break;
    				case 3: result.setSaleTypeName("外销");
    				result.setId(resultListTemp.get(i).getId());
    				result.setProducts(resultListTemp.get(i).getProducts());
    				result.setSpecifications(resultListTemp.get(i).getSpecifications());
    				result.setMemo(resultListTemp.get(i).getMemo());
    				result.setNumber(resultListTemp.get(i).getNumber());
    				switch(resultListTemp.get(i).getStatus()){
    				case 0:result.setStatusName("待检");break;
    				case 1:result.setStatusName("合格");break;
    				case 2:result.setStatusName("不合格");break;
    				}
    				resultList.add(i,result);break;
    				case 4: result.setSaleTypeName("");
    				result.setId(resultListTemp.get(i).getId());
    				result.setProducts(resultListTemp.get(i).getProducts());
    				result.setSpecifications(resultListTemp.get(i).getSpecifications());
    				result.setMemo(resultListTemp.get(i).getMemo());
    				result.setNumber(resultListTemp.get(i).getNumber());
    				switch(resultListTemp.get(i).getStatus()){
    				case 0:result.setStatusName("待检");break;
    				case 1:result.setStatusName("合格");break;
    				case 2:result.setStatusName("不合格");break;
    				}
    				resultList.add(i,result);break;
    			}
    		}
    		
    		
    	    
    	    
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
    				this.pmxListTemp = service.listWarehouseOther(user.get(0).getCanku().getId(), mydate);
    			}
    			
    		}
    			
    		pmxList = new ArrayList<ReportPmx>();
    		for(int i=0;i<pmxListTemp.size();i++){
    			if(pmxListTemp.get(i).getBhgt().equals(new BigDecimal(0))&&pmxListTemp.get(i).getWxt().equals(new BigDecimal(0))
    					&&pmxListTemp.get(i).getDjt().equals(new BigDecimal(0))&&pmxListTemp.get(i).getDxt().equals(new BigDecimal(0))
    					&&pmxListTemp.get(i).getNxt().equals(new BigDecimal(0)))
    				;
    			else
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

	public boolean isFlag() {
		return flag;
	}

	public void prepare() throws Exception {

    }
}
