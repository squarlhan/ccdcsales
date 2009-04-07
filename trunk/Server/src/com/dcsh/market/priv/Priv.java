package com.dcsh.market.priv;

import org.springframework.security.GrantedAuthority;

import com.dcsh.market.Users;

public interface Priv extends GrantedAuthority {
	// 验证一个用户对于某个资源是否有权限， 此权限包括从组继承下来的权限。
	boolean isGranted(Users user, Object Resource);

	/**
	 * 返回
	 */
	String getAuthority();
}
