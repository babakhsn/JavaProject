package stupidUni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PersonalInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldNumber;
	private JTextField textFieldDateOfBirth;
	private JButton btnNewButton_3;
	private JTextField textFieldNP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PersonalInfo frame = new PersonalInfo();
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
	public PersonalInfo(Client client) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(196, 32, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldNumber = new JTextField();
		textFieldNumber.setBounds(196, 66, 86, 20);
		contentPane.add(textFieldNumber);
		textFieldNumber.setColumns(10);
		
		textFieldDateOfBirth = new JTextField();
		textFieldDateOfBirth.setBounds(196, 107, 86, 20);
		contentPane.add(textFieldDateOfBirth);
		textFieldDateOfBirth.setColumns(10);
		
		btnNewButton_3 = new JButton("Edit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.setDateOfBirth(Integer.parseInt(textFieldDateOfBirth.getText()));
				client.setName(textFieldName.getText());
				client.setNumber(Integer.parseInt(textFieldNumber.getText()));
				client.setPassWord(textFieldNP.getText());
				client.send(textFieldName.getText() + "#" + textFieldNumber.getText() + "#" +
							textFieldDateOfBirth.getText() + "#"  + textFieldNP.getText());
			}
		});
		btnNewButton_3.setBounds(128, 208, 105, 23);
		contentPane.add(btnNewButton_3);
		
		textFieldNP = new JTextField();
		textFieldNP.setBounds(196, 148, 86, 20);
		contentPane.add(textFieldNP);
		textFieldNP.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(69, 35, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Number");
		lblNewLabel_1.setBounds(69, 69, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date Of Birth");
		lblNewLabel_2.setBounds(69, 110, 74, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New Password");
		lblNewLabel_3.setBounds(69, 151, 86, 14);
		contentPane.add(lblNewLabel_3);
	}
}
