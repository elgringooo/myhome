package com.myhome.service;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ObjSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2467501530817747052L;
	private int cnt = 0;

	public ObjSession() {
	}

	@Override
	public String toString() {
		cnt++;
		return "ObjSession : " + cnt;
	}
}
