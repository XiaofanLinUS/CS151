public class User {
	private String username;
	private String password;
	private String greeting;
	private Mailbox mailbox;
	public User(String aUsername, String aPassword) {
		username = aUsername;
		password = aPassword;
		greeting = "Welcome " + aUsername  + ".";
		mailbox = new Mailbox();
	}
	
	public boolean validate(String thePassword) {
		if (password == thePassword) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getName() {
		return username;
	}
	
	public Mailbox getmailbox() {
		return mailbox;
	}
	
	public String getGreeting()
	{
	    return greeting;
	}
}
