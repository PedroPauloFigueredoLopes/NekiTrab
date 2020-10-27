package br.com.neki.s2p2backend.model;



public class Request {
	private String login;
	private String macAdress;
	private String password;

	public Request(String login, String password, String macAdress) {
		super();
		this.login = login;
		this.macAdress = macAdress ;
		this.password = password;

	}

	public Request() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMacAdress() {
		return macAdress;
	}

	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}