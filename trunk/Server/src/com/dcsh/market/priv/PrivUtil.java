package com.dcsh.market.priv;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

public class PrivUtil {

	/**
	 * ��ȡͨ�������֤���û���Authentication���� �˶�������û������û���Ȩ�޵���Ϣ��
	 * �˷���֧�ֲ�ͬ��Ӧ�ó��������ڲ�ͬ��Ӧ���������ж��ܻ�ȡ���ƵĽ����
	 * 
	 * @return
	 */
	static public Authentication getLoginAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * ɾ����Դ������ǰ��ǰ׺�����������ǡ�url:/image/*" ����"/image/*"
	 * @param resource
	 * @param type
	 * @return
	 */
	static public String removePrefix(String resource, ResourceType type) {
		int index = resource.indexOf(type.getPrefix())
				+ type.getPrefix().length() + 1; // ��һ��ð��
		return resource.substring(index).trim();

	}
	
	/**
	 * ��ȡURL�����֣�ȥ��?�ź���Ĳ�����ͬʱҲȥ����������ַ�
	 */
	static public String getShortUrl(String url){
		int qi = url.indexOf('?'); int ai = url.indexOf('!');
		int index = qi;
		if (ai < qi && ai != -1) index = ai;
		if (index == -1) return url;
		else return url.substring(0,index);
	}
}
