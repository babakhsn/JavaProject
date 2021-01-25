package stupidUni;

import java.util.Calendar;
import java.util.Date;

public class Email {
	
	private Message[] sentMessages;
	private Message[] receivedMessages;
	private Message[] messages;
	
	public void sendMessage(String subject, String userName, String content, Date today) {
		Message message = new Message(subject, content);
		
	}
	public static void main(String[] args) {
		Date today = Calendar.getInstance().getTime();
//		today.get
		Date a = today;
		System.out.println(a);
	}
}
