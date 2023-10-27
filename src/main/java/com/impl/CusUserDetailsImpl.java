package com.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entity.Users;



public class CusUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Users user;

	



	public CusUserDetailsImpl(Users user) {
		// TODO Auto-generated constructor stub
		this.user=user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> role=new ArrayList<>();
		System.err.println(user.getRole());
		if(user.getRole()==true) {
			role.add(new SimpleGrantedAuthority("ADMIN"));
		}else {
			role.add(new SimpleGrantedAuthority("USER"));
		}
		return role;
	}
}
