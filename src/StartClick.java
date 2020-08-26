import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartClick implements MouseListener{
	
	JButton btn;
	JButton btn2;
	Container content;
	StartMain start;
	JFrame frame;
	JTextField name;
	public boolean start_click_state= false;
	
	public StartClick(JButton btn,Container contentPane,StartMain start,JFrame frame) {
		this.content = contentPane;
		this.btn = btn;
		this.start=start;
		this.frame = frame;
		JButton btn2 = null;
		name = null;
	}
	
	public StartClick(JButton btn,Container contentPane,JFrame frame,JButton btn2,JTextField name) {
		this.content = contentPane;
		this.btn = btn;
		this.btn2 = btn2;
		this.start=null;		  
		this.frame = frame;
		this.name = name;
		 
	}
	
	
	
	 
	@Override
	public void mouseClicked(MouseEvent arg0) {	
		 
		if(start!=null){
			content.remove(start);			 
		}		
		else if(start==null){			
			content.remove(btn2);
			content.remove(name);
		}		 
		
		content.remove(btn);
		
		//게임 보드 화면 
		GameBoard board = new GameBoard();
		board.setPreferredSize(new Dimension(GameBoard.CellWidth*GameBoard.CellCount,
				GameBoard.CellHeight*GameBoard.CellCount));
		board.initialize();
		
		//게임 메인 화면 
		Status state = new Status(board);
		state.setPreferredSize(new Dimension(560,100));
		
		Clock clock = new Clock(board,state);
	
		JLabel State_time = new JLabel();
		state.add(State_time);
			
		content.add(board,BorderLayout.CENTER);
		content.add(state,BorderLayout.NORTH);

		MouseListener ClickListener = new MouseClick(board,clock,state);
		board.addMouseListener(ClickListener);
		
		 
	
		clock.start();
		frame.pack();	 

	}
	 
	@SuppressWarnings("deprecation")
	@Override
	public void mouseEntered(MouseEvent e) {
		btn.setIcon(new ImageIcon("StartGame2.png"));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseExited(MouseEvent e) {
		btn.setIcon(new ImageIcon("StartGame.png"));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}
}
