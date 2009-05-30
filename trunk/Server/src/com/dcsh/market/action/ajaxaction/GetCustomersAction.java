package com.dcsh.market.action.ajaxaction;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.dcsh.market.Custom;
import com.dcsh.market.service.XiaoshouService;
import com.opensymphony.xwork2.Action;

public class GetCustomersAction implements Action{
	
	private List<Custom> customers;
	private List<String[]> names;
	private List<Custom> allcustomers;
	private String start;
    private XiaoshouService service;
	
    public GetCustomersAction(XiaoshouService service)
    {
	   System.out.println("Enter Constructor");
        this.service = service;
	}
 
	public List<Custom> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Custom> customers) {
		this.customers = customers;
	}	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}

	public List<String[]> getNames() {
		return names;
	}

	public void setNames(List<String[]> names) {
		this.names = names;
	}

	public List<Custom> getAllcustomers() {
		return allcustomers;
	}

	public void setAllcustomers(List<Custom> allcustomers) {
		this.allcustomers = allcustomers;
	}

	public String execute() {
		HanyuPinyinOutputFormat opf = new HanyuPinyinOutputFormat();
		opf.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		opf.setToneType(HanyuPinyinToneType .WITHOUT_TONE);
		opf.setVCharType(HanyuPinyinVCharType.WITH_V);
		
		
		customers = new ArrayList();
		names = new ArrayList();
		allcustomers = this.service.getAllCustom();
		if(start == null || "".equals(start.trim())) {
            start = "a";
           
        }

		for(Custom c:allcustomers){
			String[] pinyin = null;
			String result = "";
			String result_head = "";
			char[] temp = c.getCustomName().toCharArray();
			try {
				for(char cc:temp){
					//System.out.println("ccccccc:::"+cc);
					if((cc>='0'&&cc<='9')||(cc>='a'&&cc<='z')||(cc>='A'&&cc<='Z')||(cc=='(')||(cc==')')||(cc=='£¨')||(cc=='£©')){
						result+=cc;
					}else{
		                 pinyin = PinyinHelper.toHanyuPinyinStringArray(cc,opf);
		                 if(pinyin==null)result=result;
		                 else 
		                	 {
		                	     result+=pinyin[0];
		                	     result_head+=pinyin[0].charAt(0);
		                	 }     
					}
				}
				//System.out.println("rrrrrrr"+result);
					     
			} catch (BadHanyuPinyinOutputFormatCombination e1) {
				e1.printStackTrace();
			}
			
			if(result.startsWith(start.toLowerCase())||c.getCustomName().startsWith(start)||result_head.startsWith(start.toLowerCase())) { 
				customers.add(c);
				names.add(new String[]{c.getCustomName(),String.valueOf(c.getId())});
            }
			
		}
//		for(Custom ccc:customers)
//		{
//			System.out.println(ccc.getCustomName());
//		}
		
		return "show";
	}

}
