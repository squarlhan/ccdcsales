package com.dcsh.market.priv;

import org.springframework.security.GrantedAuthority;

import com.dcsh.market.Users;

public interface Priv extends GrantedAuthority {
	// ��֤һ���û�����ĳ����Դ�Ƿ���Ȩ�ޣ� ��Ȩ�ް�������̳�������Ȩ�ޡ�
	boolean isGranted(Users user, Object Resource);

	/**
	 * ����
	 */
	String getAuthority();
}
