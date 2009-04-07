package com.dcsh.market.priv;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

public class PrivUtil {

	/**
	 * 获取通过身份验证的用户的Authentication对象。 此对象包括用户名，用户的权限等信息。
	 * 此方法支持不同的应用场景，即在不同的应用上下文中都能获取类似的结果。
	 * 
	 * @return
	 */
	static public Authentication getLoginAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 删除资源描述符前的前缀。比如输入是“url:/image/*" 返回"/image/*"
	 * @param resource
	 * @param type
	 * @return
	 */
	static public String removePrefix(String resource, ResourceType type) {
		int index = resource.indexOf(type.getPrefix())
				+ type.getPrefix().length() + 1; // 有一个冒号
		return resource.substring(index).trim();

	}
	
	/**
	 * 获取URL的名字，去除?号后面的参数，同时也去掉！后面的字符
	 */
	static public String getShortUrl(String url){
		int qi = url.indexOf('?'); int ai = url.indexOf('!');
		int index = qi;
		if (ai < qi && ai != -1) index = ai;
		if (index == -1) return url;
		else return url.substring(0,index);
	}
}
