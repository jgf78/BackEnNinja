package com.udemy.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {
	public static void main(String args[]){
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("user"));
		//Pass cifrada -> $2a$10$JPoaJzh1PAVt3Nq0ZVfo.uhTLkGWcV/fbcg0QMxNNGndM1ki9HR8q
	}
	
	
}
