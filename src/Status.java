import java.awt.*;
import javax.swing.*;



//���� ���, ���ھ� , Ÿ�̸� �ð���, Ÿ�̸� ����
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
	
	
	//��ǥ�� ����̹���, ���ھ�, Ÿ�̸� �ð���, Ÿ�̸� ���� ���� 
	public void paint(Graphics g)
	{
		String counter = Integer.toString(Counter/2);
		score = Integer.toString(board.Score);
		//��� �̹���
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(img,0,0,this);		 
		
		//���ھ�
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		g.setColor(new Color(255,0,0));
		g.drawString(score, 30, 60);
		
		//Ÿ�̸� �ð��� 
		g.setColor(new Color(0,0,0));
		g.fillRect(296, 26, Counter*2+8, 48);
		g.setColor(new Color(54,234,2));
		g.fill3DRect(300, 30, Counter*2, 40,true);
		
		//Ÿ�̸� ����
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		g.setColor(new Color(255,0,0));
		g.drawString(counter, 305+Counter*2, 55);
	}
	
}
