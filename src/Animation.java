import java.awt.*;

public class Animation extends Thread{
	
	GameBoard board;  //GameBoard ���� ����
	int Source_index,Dest_index;  //�ҽ� ��ǥ 
	
	final int speed = 4;  //
	
	//Animation ������ board, Source_index, Dest_index �� �޴´� 
	public Animation(GameBoard board,int Source_index,int Dest_index)
	{
		this.board = board;  // ���� 
		this.Source_index=Source_index;  // ó���� ������ ������
		this.Dest_index=Dest_index;  // �ٲ� ������ 
	}

	public void run()
	{
		
		//�� ������ �Ÿ��� ���ǵ�� ������.
		double dx=(board.position_x[Dest_index]-board.position_x[Source_index])/speed;
		double dy=(board.position_y[Dest_index]-board.position_y[Source_index])/speed;
		
		//ó�� Source_index, Dest_index�� ��ǥ�� ���� 
		int tmp_x =board.position_x[Source_index];
		int tmp_y= board.position_y[Source_index];
		int extra_x=board.position_x[Dest_index];
		int extra_y=board.position_y[Dest_index];
		
		//speed ��ŭ ������ ����  �ѹ� �������� ������ �Ÿ� / speed ��ŭ ������ 
		for(int i=0;i<speed;i++)
		{
			try
			{	Thread.sleep(50);
			}catch(InterruptedException ex){}
			
			//���ǵ常ŭ ������ ��ġ�� �̵� 
			board.position_x[Source_index]+=dx;    
			board.position_y[Source_index]+=dy;
			board.position_x[Dest_index]-=dx;
			board.position_y[Dest_index]-=dy;
			board.repaint();	
		}	
		
		//���� ������ ���� �־��� 
		board.position_x[Source_index]=tmp_x;
		board.position_y[Source_index]=tmp_y;
		board.position_x[Dest_index]=extra_x;
		board.position_y[Dest_index]=extra_y;

		//�̹��� �ٲٱ� 
		Image Buffer_img = board.images[Dest_index];
		board.images[Dest_index]=board.images[Source_index];
		board.images[Source_index]=Buffer_img;

		//board.repaint();
	}
}
