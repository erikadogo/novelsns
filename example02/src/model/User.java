package model;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String mail;
	private String name;
	private String pass;

	public User(){

	}

	public User(String id, String name){
		this.id=id;
		this.name=name;
	}

	public User(String id, String name, String pass) {
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public User(String id, String mail, String name, String pass){
		this.id=id;
		this.mail=mail;
		this.name=name;
		this.pass=pass;
	}

	public String getId() {
		return id;
	}

	public String getMail(){
		return mail;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}


}
