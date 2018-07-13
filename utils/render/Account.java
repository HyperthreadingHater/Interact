package us.interact.utils.render;

public class Account {

	private String email;
	private String password;
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public Account(String alt) {
		this.email = alt.split(":")[0];
		this.password = alt.split(":")[1];
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toAltString() {
		return email + ":" + password;
	}
	
	public static Account fromString(String alt) {
		return new Account(alt);
	}

}
