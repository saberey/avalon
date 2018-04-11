package com.avalon.ms.dao.entity;

import lombok.Data;

@Data
public class User {
	
	private int  id;
	private String mail;
	private String username;
	private String password;
}