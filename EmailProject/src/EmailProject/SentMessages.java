package stupidUni;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SentMessages extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SentMessages frame = new SentMessages();
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
	public SentMessages(Client client, ArrayList<String> sentMessages) {

		ArrayList<JButton> btns = new ArrayList<JButton>();
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		ArrayList<JButton> btns1 = new ArrayList<JButton>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		for (int i = 0; i < sentMessages.size(); i++) {
			JLabel label = new JLabel(sentMessages.get(i));
			label.setBounds(4, i * 8, 30, 5);
			JButton btn = new JButton("See Message");
			JButton btn1 = new JButton("Delete");
			btns.add(btn);
			btns1.add(btn1);
			btn.setBounds(50, i * 8, 15, 5);
			btn1.setBounds(70, i * 8, 10, 5);
		}

		for (int j = 0; j < btns.size(); j++) {
			JButton tmpBtn = btns.get(j);
			int tmp = j;
			btns.get(j).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == tmpBtn) {
						client.send(labels.get(tmp).getText() + "#" + tmpBtn.getText());
					}
				}
			});
		}
		for (int j = 0; j < btns1.size(); j++) {
			JButton tmpBtn = btns1.get(j);
			int tmp = j;
			btns1.get(j).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == tmpBtn) {
						client.send(labels.get(tmp).getText() + "#" + tmpBtn.getText());

					}
				}
			});
		}
	}
}
