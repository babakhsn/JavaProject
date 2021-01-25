package stupidUni;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inbox extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Inbox frame = new Inbox();
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
	public Inbox(Client client, ArrayList<String> Inbox) {
		
		ArrayList<JButton> btns = new ArrayList<JButton>();
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		ArrayList<JButton> btns1 = new ArrayList<JButton>();
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
//		contentPane.add(btnNewButton, BorderLayout.CENTER);
		
		for(int i = 0; i < Inbox.size(); i++) {
			JLabel label = new JLabel(Inbox.get(i));
			label.setBounds(4 ,i*8, 30, 5);
			JButton btn = new JButton("See Message");
			JButton btn1 = new JButton("Delete");
			btns.add(btn);
			btns1.add(btn1);
			btn.setBounds(50, i*8, 15, 5);
			btn1.setBounds(70, i*8, 10, 5);
		}
		
		for(int j = 0; j < btns.size(); j++) {
			JButton tmpBtn = btns.get(j);
			int tmp = j;
			btns.get(j).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == tmpBtn) {
						client.send(labels.get(tmp).getText() + "#" + tmpBtn.getText());
					}
				}
			});
		}
		for(int j = 0; j < btns1.size(); j++) {
			JButton tmpBtn = btns1.get(j);
			int tmp = j;
			btns1.get(j).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == tmpBtn) {
						client.send(labels.get(tmp).getText() + "#" + tmpBtn.getText());
						
					}
				}
			});
		}
		
		
	}

}
