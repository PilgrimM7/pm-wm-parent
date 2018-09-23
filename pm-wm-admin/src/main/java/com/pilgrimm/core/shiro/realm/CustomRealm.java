package com.pilgrimm.core.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.pilgrimm.wm.common.user.model.User;
import com.pilgrimm.wm.common.user.service.UserService;

public class CustomRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userServie;
	
	/**
     * 认证
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		String password = ((char[]) (char[]) token.getCredentials()).toString();
		System.out.println("password:" + password.toString());
		User user = userServie.findByUsername(username);
		if (user != null) {
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(), this.getName());
			setSession("user", user);
			return info;
		}
		return null;
	}

	/**
     * 授权
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String username = (String) principalCollection.getPrimaryPrincipal();
		List<String> permissionList = new ArrayList<String>();
		permissionList.add("user:index");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissionList);
		info.addRole("admin");
		return info;
	}
	
	private void setSession(Object key, Object value) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Session session = subject.getSession();
			if (session != null)
				session.setAttribute(key, value);
		}
	}

}
