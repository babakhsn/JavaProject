package stupidUni;

import java.io.IOException;
import java.util.ArrayList;

public class Main3 {

	public static void main(String[] args) {
		Frame frame = null;
		SignUp signUp = null;
		Client client = new Client();
		EmailFrame email = null;
		ComposeEmail compose;
		FirstFrame firstFrame = new FirstFrame(client);
//		Frame frame = new Frame(client);
		firstFrame.setVisible(true);

		try {
//			receiving data from server for signing up or login in
			outer:
			while(true) {
				String serverStr = client.read();
				if (serverStr.contentEquals("Sign Up")) {
					signUp = new SignUp(client);
					signUp.setVisible(true);
					firstFrame.dispose();
					if (client.read().contentEquals("Done")) {
						signUp.dispose();
						firstFrame.setVisible(true);
						continue outer;
					} else if (client.read().contentEquals("Denied")) {
						firstFrame.setVisible(true);
						signUp.dispose();
						continue outer;
					}
				} else if (serverStr.contentEquals("Log In")) {
					firstFrame.dispose();
					frame = new Frame(client);
					frame.setVisible(true);
					break;
				}
			}
//			if client exists open email frame otherwise print out a message and go to the first frame
			if (client.in.readBoolean()) {
				email = new EmailFrame(client);
				email.setVisible(true);
				frame.dispose();
			} else {
				frame.dispose();
				firstFrame.setVisible(true);
			}
			String str = client.read();
			if (str.contentEquals("Compose An Email")) {
				compose = new ComposeEmail(client);
				compose.setVisible(true);
				email.dispose();
			} else if (str.contentEquals("Inbox")) {

				email.dispose();
			} else if (str.contentEquals("Sent Messages")) {

				email.dispose();
			} else if (str.contentEquals("Edit Personal Info")) {
				PersonalInfo pinf = new PersonalInfo(client);
				pinf.setVisible(true);
				email.dispose();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
