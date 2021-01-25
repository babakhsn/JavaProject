package stupidUni;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EmailFrame extends JFrame {
	
	
	
//	private Client client;
//	
//	public void setClient(Client client) {
//		this.client = client;
//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7501792938414348504L;
	private JPanel contentPane;
//	protected static Client client;

	/**
	 * Launch the application.
	 */
	
//	public void setClient(Client client) {
//		EmailFrame.client = client;
//	}
//	
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EmailFrame email = new EmailFrame();
//					email.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public EmailFrame(Client client) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Compose An Email");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					client.send(btnNewButton.getText());
				}
			}
		});
		btnNewButton.setBounds(141, 36, 159, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Inbox");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_1) {
					client.send(btnNewButton_1.getText());
//					System.out.println(Client.clients.size());
				}
			}
		});
		btnNewButton_1.setBounds(181, 70, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sent Emails");
		btnNewButton_2.setBounds(181, 104, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Edit Personal Info");
		btnNewButton_3.setBounds(141, 138, 159, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Exit");
		btnNewButton_4.setBounds(181, 172, 89, 23);
		contentPane.add(btnNewButton_4);
	}
}
