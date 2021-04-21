package com.excilys.cdb.model;

public class User {

	private int id;
	private String authority;
	private String username;
	private String password;
	private boolean enabled;

	public User(int id, String authority, String username, String password, boolean enabled) {
		super();
		this.id = id;
		this.authority = authority;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String authority, String username, String password, boolean enabled) {

		this.authority = authority;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}