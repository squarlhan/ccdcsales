package com.dcsh.market.priv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.dcsh.market.Users;

/**
 * 用来从数据库中读取身份信息的类
 * @author wangkp
 *
 */
public class UserAndGroupAuthenticationImpl implements AuthenticationProvider {
	private static Logger log = null;
	static{
		log = Logger.getLogger(UserAndGroupAuthenticationImpl.class.getName());
	}

	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;  
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * 得到一个用户的权限。
	 * 返回的Authentication对象中,getPriprile是一个User对象，
	 * @param auth
	 *   此结构的getPripripl需要返回一个字符串对象。
	 *   getCredial返回一个byte[],存储的是用户的密码。 可以考虑使用UsernamePasswordAuthenticationToken
	 *   作为传入参数。
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {

		String name = null;
		String pwd = null;
		try{
	
				name = (String)auth.getPrincipal();
				pwd = (String)auth.getCredentials();
			
		}catch(Exception e){
			throw new IllegalArgumentException("错误的参数!" + e.getMessage());
		}
		
		//验证用户是不是合法用户
		List<Users> users = 
			hibernateTemplate.find("from Users where name = '" +  name.trim() + "'");
		if (users.size() == 0) 
			throw new UsernameNotFoundException(name);
		Users user = users.iterator().next();
		//验证用户
		if (!Arrays.equals(pwd.getBytes(), user.getPassword())) 
			throw new BadCredentialsException("密码错误！");
		
		
		PrivAuthenticationImpl rst = 
			new PrivAuthenticationImpl(user);
		
		//提取用户的权限,使用SQL来提取
		String usql = "select * from UserPriv where UID = " + user.getId();
		this.getGrantedAuthFromSql(rst, usql);
		
		//提取用户所在组的权限
		String gsql = "select ugp.* from UserGroupPriv ugp join UserGroup ug " +
		"on ugp.gid = ug.id join UserRelGroup urg on ug.id = urg.gid join Users u on urg.uid = u.id where u.id = " 
		+ user.getId();
		this.getGrantedAuthFromSql(rst, gsql);
		
		rst.setAuthenticated(true); //设置验证的状态。
		return rst;

	}

    public boolean supports(Class authentication){
    	return true;
    }
    
    private void getGrantedAuthFromSql(final PrivAuthenticationImpl authorities, final String sql){
    	final ArrayList<String> resources = new ArrayList<String>();
		hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				session.doWork(new Work(){
					public void execute(Connection connection) throws SQLException{
						Statement stmt = connection.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						
						while(rs.next()){
							resources.add(rs.getString("Resource"));
						}
						stmt.close();
					}
				});
				return null;
			}
		});
		
		//如果需要，把基于字符串的资源描述转换成具体的对象。并添加到用户的GrantedAuth里去。
		hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				for(String rd: resources){
					ResourceType type = ResourceType.buildFromString(rd); //获取类型
					if (type == null){
						log.warning(rd+"不是合法的资源描述符！");
						continue;
					}
					rd = PrivUtil.removePrefix(rd, type);
					Object res = null;
					ClassMetadata cm = 
						hibernateTemplate.getSessionFactory().getClassMetadata(type.getResClass());
					if (cm != null){
			
						res = session.get(type.getResClass(), Integer.valueOf(rd));
					}else
						res = rd;
					if (res == null){
						log.warning(res+"描述的资源不存在！");
						continue;
					}					
					ResourceGrantedAuthorityImpl ga = new ResourceGrantedAuthorityImpl(type,res);
					authorities.addGrantedAuthority(ga);
				}
				return null;
			}			
		});
    }
    
}
