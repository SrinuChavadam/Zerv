package com.zerv.unittests;

import com.zerv.crypto.utils.PasswordEncrypt;

public class EncryptYourPassword {
	
	public static void main(String[] args)
			
	{
	PasswordEncrypt obj=new PasswordEncrypt();
	String encrypt = null;
	try {
		encrypt = obj.encrypt("onboarding_user");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Encrypted password is :" +encrypt);
	
	}
	

}
