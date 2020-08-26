import java.awt.*;
import javax.swing.*;



//상태 배경, 스코어 , 타이머 시간바, 타이머 숫자
public class Status extends JPanel{
	Image img = getToolkit().getImage("upbackground.png");
	String score;
	GameBoard board;
	public int Counter;
	
	public Status(){
		
	}
	public Status(GameBoard board)
	{
		this.board = board;
	}
	
	
	//좌표로 배경이미지, 스코어, 타이머 시간바, 타이머 숫자 구분 
	public void paint(Graphics g)
	{
		String counter = Integer.toString(Counter/2);
		score = Integer.toString(board.Score);
		//배경 이미지
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(img,0,0,this);		 
		
		//스코어
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		g.setColor(new Color(255,0,0));
		g.drawString(score, 30, 60);
		
		//타이머 시간바 
		g.setColor(new Color(0,0,0));
		g.fillRect(296, 26, Counter*2+8, 48);
		g.setColor(new Color(54,234,2));
		g.fill3DRect(300, 30, Counter*2, 40,true);
		
		//타이머 숫자
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		g.setColor(new Color(255,0,0));
		g.drawString(counter, 305+Counter*2, 55);
	}
	
}
