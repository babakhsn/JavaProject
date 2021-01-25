package stupidUni;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9106736615425512675L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
//		Server.MyThread.threads.get(1).client = client;
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Frame frame = new Frame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public Frame(Client client) {
//		Client client = new Client();
//		Client.clients.add(client);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) {
					client.send(textField.getText() + "#" + textField_1.getText() + "#" + btnNewButton.getText());
					client.setUserName(textField.getText());
					client.setPassWord(textField_1.getText());
				}
			}
		});

		btnNewButton.setBounds(118, 141, 89, 23);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(118, 53, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(118, 94, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(21, 56, 54, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(21, 97, 46, 14);
		contentPane.add(lblNewLabel_1);
	}
}
