package com.dcsh.market.priv;

import org.springframework.security.GrantedAuthority;

public class ResourceGrantedAuthorityImpl implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResourceType type;
	private Object resource;
	
	public ResourceGrantedAuthorityImpl(ResourceType type, Object resource){
		this.type = type;
		this.resource = resource;
	}
	
	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public Object getResource() {
		return resource;
	}

	public void setResource(Object resource) {
		this.resource = resource;
	}

	@Override
	public String getAuthority() {
		return null;
	}

	@Override
	public int compareTo(Object o) {
		if (o != null && o instanceof ResourceGrantedAuthorityImpl) {
			ResourceGrantedAuthorityImpl attr = (ResourceGrantedAuthorityImpl) o;
			if (this.type != attr.type) return this.type.compareTo(attr.type);
			if (this.resource.equals(attr.resource)) return 0;
		}
		return -1;
	}

	public boolean equals(Object obj) {
        if (obj instanceof ResourceGrantedAuthorityImpl) {
        	ResourceGrantedAuthorityImpl attr = (ResourceGrantedAuthorityImpl) obj;

            return (this.type.equals(attr.type) && this.resource.equals(attr.resource));
        }

        return false;
    }
	
    public int hashCode() {
        int code = 31;
        code = type.hashCode() * 17 + resource.hashCode();
        return code;
    }

}
