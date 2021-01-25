package stupidUni;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ComposeEmail extends JFrame {
	
//	private Client client;
//	
//	public void setClient(Client client) {
//		this.client = client;
//	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 745864798544557178L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ComposeEmail frame = new ComposeEmail();
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
	public ComposeEmail(Client client) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(141, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 47, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(141, 78, 227, 134);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(textField.getText() + "#" + textField_1.getText() + "#" + textArea.getText());
				
			}
		});
		btnNewButton.setBounds(165, 216, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel = new JLabel("Username of Receiver");
		lblNewLabel.setBounds(21, 14, 89, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Subject");
		lblNewLabel_1.setBounds(45, 50, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Text");
		lblNewLabel_2.setBounds(45, 77, 46, 14);
		contentPane.add(lblNewLabel_2);
	}
}
