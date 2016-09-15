public class User {
	private String username;
	private String password;
	private String greeting;
	private Mailbox mailbox;
	
	/**
	 * Constructs a User object with a given name and password
	 * @param aUsername the name
	 * @param aPassword the password
	 */
	public User(String aUsername, String aPassword) {
		username = aUsername;
		password = aPassword;
		greeting = "Welcome " + aUsername + ".";
		mailbox = new Mailbox();
	}
	
	/**
	 * Validate the password 
	 * @param thePassword the password
	 * @return true if it is right else false
	 */
	public boolean validate(String thePassword) {
		if (password.equals(thePassword)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Get the account name
	 * @return name
	 */
	public String getName() {
		return username;
	}
	
	/**
	 * Get the account's mailbox
	 * @return the mailbox
	 */
	public Mailbox getmailbox() {
		return mailbox;
	}
	
	/**
	 * Get the account's greeting
	 * @return the greeting
	 */
	public String getGreeting()
	{
	    return greeting;
	}
}
