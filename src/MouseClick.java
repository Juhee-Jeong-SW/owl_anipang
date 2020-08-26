import java.awt.Image;
import java.awt.event.*;

public class MouseClick implements MouseListener{

	GameBoard board;
	Clock clock;
	Status state;
	int Click_index,Up_index;	 
	int Click_col,Click_row,Up_col,Up_row;
 
	boolean ClickState = false;
	
	Animation ani;
	Thread time;
 
	public MouseClick(GameBoard board , Clock clock,Status state) {
		this.board = board;
		this.clock = clock;
		this.state = state;
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	//마우스가 눌렷을때. 
	//최초의 포인트를 기억.
	public void mousePressed(MouseEvent e) {
  
		
		if(clock.run==false) return;
  
		Click_col = e.getX()/GameBoard.CellWidth;
		Click_row = e.getY()/GameBoard.CellHeight;
  
		Click_index = Click_row*GameBoard.CellCount+Click_col;
		  board.source_index =Click_index;
		  if(board.images[Click_index]==board.getToolkit().getImage("image9.png"))
		  {
		   board.Boom(Click_index);
		  }
	}
	//마우스가 업됫을때.
	//최후의 포인트를 기억.
	public void mouseReleased(MouseEvent e) {  
 

		if(clock.run==false) return;

		Up_col = e.getX()/GameBoard.CellWidth;
		Up_row = e.getY()/GameBoard.CellHeight;

		//2칸이상이나 대각선으로 이동할시 이동하지 않음.
		if(!((Math.abs(Up_col-Click_col)==1&&Math.abs(Up_row-Click_row)==0)||
				(Math.abs(Up_col-Click_col)==0&&Math.abs(Up_row-Click_row)==1))) return;

		Up_index=Up_row*GameBoard.CellCount+Up_col;
		board.dest_index = Up_index;

		ani = new Animation(board,Click_index,Up_index);
		ani.start();

		try
		{ Thread.sleep(10);
		}catch(InterruptedException ex){}
		
		board.Match(Click_index,Up_index);
		
		try
		{ Thread.sleep(10);
		}catch(InterruptedException ex){}
		
		
		if(board.Match(Click_index,Up_index))
		{
			   System.out.print("찾는게 없어용");
			   
			   ani = new Animation(board,Click_index,Up_index);
			   ani.start();
			   try
			   { Thread.sleep(5);
			   }catch(InterruptedException ex){}
		}
		else{
			System.out.print("찾았어용");
			Sound sound = new Sound();
			sound.getSound("sound 22.wav");
		}
		board.Search();
		board.down();
	
	}
}
