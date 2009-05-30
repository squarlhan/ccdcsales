package com.dcsh.market.priv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.providers.AbstractAuthenticationToken;

import com.dcsh.market.Users;

/**
 * 认证的实现类。
 * 添加了一些附加的接口，可以获取拥有的特定资源的能力。
 * @author wangkp
 *
 */
public class PrivAuthenticationImpl implements Authentication {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2869719482516066282L;
	
	private Users user;
    private Object details;
    private TreeMap<ResourceType,HashSet<ResourceGrantedAuthorityImpl>> authorities = 
    	new TreeMap<ResourceType,HashSet<ResourceGrantedAuthorityImpl>>();
    private boolean authenticated = false;
	
	public PrivAuthenticationImpl(Users user){
		this.user = user;
	}
	

	/**
	 * 获取指定资源的授权。比如如果传入的参数是 ResourceType.CANKU
	 * 则返回此次登陆可以访问的仓库对象。
	 * @param clasz
	 * @return
	 */
	public List<ResourceGrantedAuthorityImpl> getGrantedAuthorityResource(ResourceType type){
		HashSet<ResourceGrantedAuthorityImpl> list = authorities.get(type);
		ArrayList<ResourceGrantedAuthorityImpl> rst = new ArrayList<ResourceGrantedAuthorityImpl>();
		if (list != null) rst.addAll(list);
		return rst;
	}
	
    public GrantedAuthority[] getAuthorities() {
        if (authorities == null) {
            return new GrantedAuthority[0];
        }

        GrantedAuthority[] copy = new GrantedAuthority[getAuthoritySize()];
        int pos = 0;
    	for(HashSet<ResourceGrantedAuthorityImpl> list: authorities.values()){
    		System.arraycopy(list.toArray(new GrantedAuthority[list.size()]), 0, copy, pos, list.size());
    		pos += list.size();
    	}
        return copy;
    }

    public void addGrantedAuthority(ResourceGrantedAuthorityImpl authority){
    	HashSet<ResourceGrantedAuthorityImpl> list = authorities.get(authority.getType());
    	if (list == null){
    		list = new HashSet<ResourceGrantedAuthorityImpl>();
    		authorities.put(authority.getType(), list);
    	}
    	list.add(authority);
    }
    
    public int getAuthoritySize(){
    	int size = 0;
    	for(HashSet list: authorities.values()){
    		size += list.size();
    	}
    	return size;
    }
    
    public Object getCredentials(){
    	return user.getPassword();
    }

    public Users getPrincipal(){
    	return user;
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Object getDetails() {
        return details;
    }


    //~ Methods ========================================================================================================

    public boolean equals(Object obj) {
        if (obj instanceof AbstractAuthenticationToken) {
            AbstractAuthenticationToken test = (AbstractAuthenticationToken) obj;

            if (!((this.getAuthorities() == null) && (test.getAuthorities() == null))) {
                if ((this.getAuthorities() == null) || (test.getAuthorities() == null)) {
                    return false;
                }

                if (this.getAuthorities().length != test.getAuthorities().length) {
                    return false;
                }

                for (int i = 0; i < this.getAuthorities().length; i++) {
                    if (!this.getAuthorities()[i].equals(test.getAuthorities()[i])) {
                        return false;
                    }
                }
            }

            if ((this.details == null) && (test.getDetails() != null)) {
                return false;
            }

            if ((this.details != null) && (test.getDetails() == null)) {
                return false;
            }

            if ((this.details != null) && (!this.details.equals(test.getDetails()))) {
                return false;
            }

            if ((this.getCredentials() == null) && (test.getCredentials() != null)) {
                return false;
            }

            if ((this.getCredentials() != null) && !this.getCredentials().equals(test.getCredentials())) {
                return false;
            }
            
            if (this.getPrincipal() == null && test.getPrincipal() != null) {
                return false;
            }

            if (this.getPrincipal() != null && !this.getPrincipal().equals(test.getPrincipal())) {
                return false;
            }            
            
            return this.isAuthenticated() == test.isAuthenticated();
        }

        return false;
    }


    public int hashCode() {
        int code = 31;

        // Copy authorities to local variable for performance (SEC-223)
        GrantedAuthority[] authorities = this.getAuthorities();

        if (authorities != null) {
            for (int i = 0; i < authorities.length; i++) {
                code ^= authorities[i].hashCode();
            }
        }

        if (this.getPrincipal() != null) {
            code ^= this.getPrincipal().hashCode();
        }

        if (this.getCredentials() != null) {
            code ^= this.getCredentials().hashCode();
        }

        if (this.getDetails() != null) {
            code ^= this.getDetails().hashCode();
        }

        if (this.isAuthenticated()) {
            code ^= -37;
        }

        return code;
    }
    

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString()).append(": ");
        sb.append("Principal: ").append(this.getPrincipal()).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Authenticated: ").append(this.isAuthenticated()).append("; ");
        sb.append("Details: ").append(this.getDetails()).append("; ");

        if (this.getAuthorities() != null) {
            sb.append("Granted Authorities: ");

            for (int i = 0; i < this.getAuthorities().length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }

                sb.append(this.getAuthorities()[i].toString());
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
