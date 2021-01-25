package stupidUni;

import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient String message;
	private transient String subject;
	private transient Date today = Calendar.getInstance().getTime();
	
	Message() {}
	
	Message(String subject, String content) {
		this.message = content;
		this.subject = subject;
//		this.today = today;
	}
	
	public String getContent() {
		return message;
	}
	public void setContent(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getToday() {
		return today;
	}
//	public void setToday(Date today) {
//		this.today = today;
//	}
	
	
}
