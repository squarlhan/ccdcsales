package com.dcsh.market.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

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
	 * @throws IOException 
	 */
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination, IOException {
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
		
		System.out.println("****************************");
		List<Integer> l = new ArrayList();
		l.add(10);
		l.add(5);
		l.add(10);
		l.add(10);
		l.add(10);
		
		int a = Integer.valueOf(JOptionPane.showInputDialog("the input:")); 
		System.out.println("a:"+a);
		int i = 0;
		int sum = 0;
		int aa = a;
		while(sum<a){
			int t = l.get(i);
			sum+=t;
			i++;
		}
		System.out.println("i:"+i);
		for(int j=0;j<=i-1;j++){
			if(j!=i-1){
				aa-=l.get(j);
				System.out.println(j+":"+l.get(j));
			}
			else System.out.println(j+":"+aa);
		}
		
		

	}

}
