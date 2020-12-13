package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiMainView extends JFrame {

	private JTextField textField = new JTextField(10);
	private JButton button = new JButton("검색");
	private JPanel panel = new JPanel();

	private JLabel googleMap = new JLabel();
	private String location = "서울";

	JLabel label1 = new JLabel("S : 판매자(사용자)");
	JLabel label2 = new JLabel("B : 구매자");

	public GuiMainView(String appuserId) {

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		panel.add(label1);
		panel.add(label2);
//		panel.add(textField);
//		panel.add(button);
		button.addMouseListener(new Event());

		add(googleMap);

		setTitle("google Maps");
		setVisible(true);

		add(BorderLayout.EAST, panel);
		add(BorderLayout.WEST, googleMap);

		pack();
	}

	public class Event implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

	}

}
