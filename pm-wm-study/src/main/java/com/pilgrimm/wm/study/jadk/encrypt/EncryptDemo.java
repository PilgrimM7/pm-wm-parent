package com.pilgrimm.wm.study.jadk.encrypt;

import com.pilgrimm.core.util.AesUtil;

public class EncryptDemo {
	
	public static void main(String[] args) {
		System.out.println(AesUtil.encrypt("pilgrimm"));
		
		System.out.println(AesUtil.decrypt(AesUtil.encrypt("pilgrimm")));
	}

}
