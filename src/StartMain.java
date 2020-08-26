import java.awt.Graphics;
import java.awt.Image;


import javax.swing.JPanel;


public class StartMain extends JPanel  {
	
	Image img = getToolkit().getImage("StartBackground.png");
	
	public void paint(Graphics g)
	{
		g.drawImage(img,0,0,this);
		g.drawImage(getToolkit().getImage("start_tumblr.gif"),480,150 ,this);	
	}
	
}
