import java.util.ArrayList;
import java.util.Scanner;

public class Console {

	public Console(MailSystem aSys, Scanner aScanner) {
		sys = aSys;
		scanner = aScanner;
		buff = new ArrayList<>();
	}

	public void display(String output) {
		System.out.println(output);
	}

	public void run() {
		boolean more = true;
		while (more) {
			display(outputs[state + step]);
			String input = scanner.next();
			if (input == null)
				return;
			more = execute(input);
		}
	}

	public boolean execute(String input) {
		if (state == MAIN_MENU) {
			if (input.equalsIgnoreCase("L")) {
				outputs[MAIN_MENU] = "Welcome to TextChat.\n[L]ogin  [N]ew user  [Q]uit:";
				state = SIGNIN;
			} else if (input.equalsIgnoreCase("N")) {
				outputs[MAIN_MENU] = "Welcome to TextChat.\n[L]ogin  [N]ew user  [Q]uit:";
				state = SIGNUP;
			} else if (input.equalsIgnoreCase("Q")) {
				return false;
			}else {
				outputs[MAIN_MENU] = "[L]ogin  [N]ew user  [Q]uit:";
			}
		} else if (state == USER_MENU) {
			if (input.equalsIgnoreCase("S")) {
				state = SENDMESSAGE;
			} else if (input.equalsIgnoreCase("R")) {
				readIt();
			} else if (input.equalsIgnoreCase("L")) {
				sys.logOut();
				state = MAIN_MENU;
			}
		} else if (state == SIGNIN) {
			signIn(input);
		} else if (state == SIGNUP) {
			signUp(input);
		} else if (state == SENDMESSAGE) {
			sendIt(input);
		} else if (state == MESSAGE_MENU1) {
			if (input.equalsIgnoreCase("K")) {
				sys.keepMessage();
			}
			else if (input.equalsIgnoreCase("E")) {
				sys.eraseMessage();
			}
			state = MESSAGE_MENU2;
		} else if (state == MESSAGE_MENU2) {
			if (input.equalsIgnoreCase("N")) {
				readIt();
			}
			else if (input.equalsIgnoreCase("D")) {
				state = USER_MENU;
			}
		}
		return true;
	}

	private void readIt() {
		display(sys.readMessage());
		state = MESSAGE_MENU1;
	}

	private void sendIt(String input) {
		boolean success = false;
		if (step == 0) {
			buff.add(input);
			step++;
		} else if (step >= 1) {
			String content = "";
			content += input;
			while (!input.equals(".")) {
				input = scanner.nextLine();
				content += input;
				content += "\n";
			}
			content = content.substring(0, content.length() - 3);
			buff.add(content);
			success = sys.sendMessage(buff.get(0), buff.get(1));
			buff = new ArrayList<>();
			step = 0;
			state = USER_MENU;
			if(success) {
				display("Message sent.");
			}else {
				display("Message not sent.");
			}
		}
	}

	private void signUp(String input) {
		if (step == 0) {
			buff.add(input);
			step++;
		} else if (step == 1) {
			buff.add(input);
			sys.register(buff.get(0), buff.get(1));
			buff = new ArrayList<>();
			step = 0;
			state = MAIN_MENU;
			//display("User added.");
		}
	}

	private void signIn(String input) {
		if (step == 0) {
			buff.add(input);
			step++;
		} else if (step == 1) {
			int errorCode = 0;
			buff.add(input);
			errorCode = sys.logIn(buff.get(0), buff.get(1));
			buff = new ArrayList<>();
			step = 0;
			if(errorCode < 0) {
				state = MAIN_MENU;
				return;
			}
			state = USER_MENU;
		}
	}

	private Scanner scanner;
	private MailSystem sys;
	private ArrayList<String> buff;

	private static final int MAIN_MENU = 0;
	private static final int USER_MENU = 1;
	private static final int MESSAGE_MENU1 = 2;
	private static final int MESSAGE_MENU2 = 3;
	private static final int SIGNIN = 4;
	private static final int SIGNUP = 6;
	private static final int SENDMESSAGE = 8;
	private static final String[] outputs = { "Welcome to TextChat.\n[L]ogin  [N]ew user  [Q]uit:",
			"[S]end message  [R]ead messages  [L]ogout:", "[K]eep  [E]rase", "[N]ext  [D]one with messages",
			"User name:", "Password:", "Select a user name:", "Select a password:", "To:", "Message text, . to end:" };
	private int state = 0;
	private int step = 0;
}
