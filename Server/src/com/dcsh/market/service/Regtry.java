package com.dcsh.market.service;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Regtry {

	/**
	 * @param args
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		// TODO Auto-generated method stub
		String reg = ".*(/warehouseadmin/|/cyc|/index).*";
		String str = "http://localhost:8080/Server/cycloginchuyunchuAdmin.action";
		Pattern p = Pattern.compile(reg);
		if(p.matcher(str).matches())System.out.println("000000000000");
		else System.out.println("************");
		HanyuPinyinOutputFormat opf = new HanyuPinyinOutputFormat();
		opf.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		opf.setToneType(HanyuPinyinToneType .WITHOUT_TONE);
		opf.setVCharType(HanyuPinyinVCharType.WITH_V);
		char[] name = "¿Í»§".toCharArray();
		String[] pinyin = null;
		String result = "";
		for(char cc:name){
		   pinyin = PinyinHelper.toHanyuPinyinStringArray(cc,opf);	
		   result+=pinyin[0];
		}
		

	}

}
