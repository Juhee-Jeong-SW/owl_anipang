import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class MainP extends JPanel{

	public static void main(String args[])
	{		
		JFrame frame = new JFrame();       
		Sound sound = new Sound();
		frame.setLocation(100,10);
		Container contentPane = frame.getContentPane();
		sound.getSound("anipang_ingame.wav");
		
		StartMain start = new StartMain();
		start.setPreferredSize(new Dimension(700,422));
		start.setLayout(null);
		contentPane.add(start);		
		
		final JButton btn = new JButton(new ImageIcon("StartGame.png"));
		btn.setSize(164,70);
		btn.setBounds(20,320,164,70);	
		
		start.add(btn);		 		 
		
		StartClick startClick = new StartClick(btn,contentPane,start,frame);
		btn.addMouseListener(startClick);		
 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close 버튼의 이벤트 핸들러 추가
		frame.pack();
		frame.setVisible(true);
		
	}


}