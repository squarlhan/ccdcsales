package com.dcsh.market.priv;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.springframework.security.AccessDecisionManager;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.InsufficientAuthenticationException;
import org.springframework.security.intercept.web.FilterInvocation;

/**
 * 验证管理器，决定是否对某个资源有访问权限。
 * 
 * @author wangkp
 * 
 */
public class UserAndGroupAccessDecisionManager implements AccessDecisionManager {

	static HashMap<Class,AccessDecisionManager> dm = null;
	static{
		dm = new HashMap<Class,AccessDecisionManager>();
		//dm.put(CankuResource.class, new CankuAccessDecisionManager());
		//dm.put(URLResource.class, new URLAccessDecisionManager());
		dm.put(FilterInvocation.class, new FilterInvocationDecisionManager());
	}
	
	@Override
	public void decide(Authentication authentication, Object object,
			ConfigAttributeDefinition config) throws AccessDeniedException,
			InsufficientAuthenticationException {
//		System.out.println(authentication);
//		System.out.println(object);
//		System.out.println(config);

		AccessDecisionManager manager = dm.get(object.getClass());
		if (manager == null) throw new IllegalArgumentException("不支持的验证类型");
		manager.decide(authentication, object, config);

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return dm.get(clazz) != null;
		//return PrivResource.class.isAssignableFrom(clazz);
	}

}

class FilterInvocationDecisionManager implements AccessDecisionManager{
	static final String URL = "url";
	public void decide(Authentication authentication, Object object,
			ConfigAttributeDefinition config) throws AccessDeniedException,
			InsufficientAuthenticationException {
		if (!(object instanceof FilterInvocation))
			throw new IllegalArgumentException("不支持的验证类型");
		PrivAuthenticationImpl myAuth = (PrivAuthenticationImpl)authentication;
		FilterInvocation url = (FilterInvocation) object;
		boolean isGranted = false;
		for (ResourceGrantedAuthorityImpl ga : myAuth.getGrantedAuthorityResource(ResourceType.URL)) {
			String auth = (String)ga.getResource();
			Pattern p = Pattern.compile(auth);
			//String queryUrl = PrivUtil.getShortUrl(url.getRequestUrl());
			if (p.matcher(url.getRequestUrl()).matches()){
				isGranted = true;
				break;
			}
		}
		if (!isGranted)
			throw new AccessDeniedException("没有访问" + url.getFullRequestUrl()
					+ "的权限。");
		
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}


//class CankuAccessDecisionManager implements AccessDecisionManager {
//	@Override
//	public void decide(Authentication authentication, Object object,
//			ConfigAttributeDefinition config) throws AccessDeniedException,
//			InsufficientAuthenticationException {
//		if (!(object instanceof CankuResource))
//			throw new IllegalArgumentException("不支持的验证类型");
//		boolean isGranted = false;
//		CankuResource res = (CankuResource) object;
//		for (GrantedAuthority ga : authentication.getAuthorities()) {
//			String auth = ga.getAuthority();
//			if (!auth.startsWith(res.getPrefix()))
//				throw new IllegalArgumentException("错误的资源描述类型");
//			int index = auth.indexOf(res.getPrefix())
//					+ res.getPrefix().length() + 1; //有一个冒号
//			int id = Integer.parseInt(auth.substring(index).trim());
//			if (id == res.getCanku().getId()){
//				isGranted = true;
//				break;
//			}
//		}
//		if (!isGranted)
//			throw new AccessDeniedException("没有访问" + res.getCanku().getName()
//					+ "的权限。");
//	}
//
//	@Override
//	public boolean supports(ConfigAttribute attribute) {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean supports(Class clazz) {
//		return CankuResource.class.isAssignableFrom(clazz);
//	}
//
//}

//class URLAccessDecisionManager implements AccessDecisionManager {
//	@Override
//	public void decide(Authentication authentication, Object object,
//			ConfigAttributeDefinition config) throws AccessDeniedException,
//			InsufficientAuthenticationException {
//		if (!(object instanceof URLResource))
//			throw new IllegalArgumentException("不支持的验证类型");
//		URLResource url = (URLResource) object;
//		boolean isGranted = false;
//		for (GrantedAuthority ga : authentication.getAuthorities()) {
//			String auth = ga.getAuthority();
//			if (!auth.startsWith(url.getPrefix()))
//				throw new IllegalArgumentException("错误的资源描述类型");
//			int index = auth.indexOf(url.getPrefix())
//					+ url.getPrefix().length() + 1; //有一个冒号
//			String gurl = auth.substring(index).trim();
//			Pattern p = Pattern.compile(gurl);
//			if (p.matcher(url.getUrl()).matches()){
//				isGranted = true;
//				break;
//			}
//		}
//		if (!isGranted)
//			throw new AccessDeniedException("没有访问" + url.getUrl()
//					+ "的权限。");
//	}
//
//	@Override
//	public boolean supports(ConfigAttribute attribute) {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean supports(Class clazz) {
//		return URLResource.class.isAssignableFrom(clazz);
//	}
//
//}
