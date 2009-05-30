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
 * ���������ݿ��ж�ȡ�����Ϣ����
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
	 * �õ�һ���û���Ȩ�ޡ�
	 * ���ص�Authentication������,getPriprile��һ��User����
	 * @param auth
	 *   �˽ṹ��getPripripl��Ҫ����һ���ַ�������
	 *   getCredial����һ��byte[],�洢�����û������롣 ���Կ���ʹ��UsernamePasswordAuthenticationToken
	 *   ��Ϊ���������
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
			throw new IllegalArgumentException("����Ĳ���!" + e.getMessage());
		}
		
		//��֤�û��ǲ��ǺϷ��û�
		List<Users> users = 
			hibernateTemplate.find("from Users where name = '" +  name.trim() + "'");
		if (users.size() == 0) 
			throw new UsernameNotFoundException(name);
		Users user = users.iterator().next();
		//��֤�û�
		if (!Arrays.equals(pwd.getBytes(), user.getPassword())) 
			throw new BadCredentialsException("�������");
		
		
		PrivAuthenticationImpl rst = 
			new PrivAuthenticationImpl(user);
		
		//��ȡ�û���Ȩ��,ʹ��SQL����ȡ
		String usql = "select * from UserPriv where UID = " + user.getId();
		this.getGrantedAuthFromSql(rst, usql);
		
		//��ȡ�û��������Ȩ��
		String gsql = "select ugp.* from UserGroupPriv ugp join UserGroup ug " +
		"on ugp.gid = ug.id join UserRelGroup urg on ug.id = urg.gid join Users u on urg.uid = u.id where u.id = " 
		+ user.getId();
		this.getGrantedAuthFromSql(rst, gsql);
		
		rst.setAuthenticated(true); //������֤��״̬��
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
		
		//�����Ҫ���ѻ����ַ�������Դ����ת���ɾ���Ķ��󡣲���ӵ��û���GrantedAuth��ȥ��
		hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session){
				for(String rd: resources){
					ResourceType type = ResourceType.buildFromString(rd); //��ȡ����
					if (type == null){
						log.warning(rd+"���ǺϷ�����Դ��������");
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
						log.warning(res+"��������Դ�����ڣ�");
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
