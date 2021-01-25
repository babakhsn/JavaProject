import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		Dimension d = new Dimension(width, height);
		
		frame.setPreferredSize(d);
		frame.setMaximumSize(d);
		frame.setMinimumSize(d);
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowHandler(game));
	}

}
