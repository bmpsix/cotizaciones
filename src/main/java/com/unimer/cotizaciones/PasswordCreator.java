package com.unimer.cotizaciones;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("passwordCreator")
public class PasswordCreator extends BCryptPasswordEncoder{

	public String EncodePass(String password){
		return encode(password);
	}

}
