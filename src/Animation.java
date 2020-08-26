import java.awt.*;

public class Animation extends Thread{
	
	GameBoard board;  //GameBoard 객제 생성
	int Source_index,Dest_index;  //소스 좌표 
	
	final int speed = 4;  //
	
	//Animation 생성자 board, Source_index, Dest_index 를 받는다 
	public Animation(GameBoard board,int Source_index,int Dest_index)
	{
		this.board = board;  // 보드 
		this.Source_index=Source_index;  // 처음에 눌려진 아이콘
		this.Dest_index=Dest_index;  // 바뀔 아이콘 
	}

	public void run()
	{
		
		//총 움직일 거리를 스피드로 나눈것.
		double dx=(board.position_x[Dest_index]-board.position_x[Source_index])/speed;
		double dy=(board.position_y[Dest_index]-board.position_y[Source_index])/speed;
		
		//처음 Source_index, Dest_index의 좌표를 저장 
		int tmp_x =board.position_x[Source_index];
		int tmp_y= board.position_y[Source_index];
		int extra_x=board.position_x[Dest_index];
		int extra_y=board.position_y[Dest_index];
		
		//speed 만큼 루프를 돈다  한번 돌때마다 움직일 거리 / speed 만큼 움직임 
		for(int i=0;i<speed;i++)
		{
			try
			{	Thread.sleep(50);
			}catch(InterruptedException ex){}
			
			//스피드만큼 목적지 위치로 이동 
			board.position_x[Source_index]+=dx;    
			board.position_y[Source_index]+=dy;
			board.position_x[Dest_index]-=dx;
			board.position_y[Dest_index]-=dy;
			board.repaint();	
		}	
		
		//원래 포지션 값을 넣어줌 
		board.position_x[Source_index]=tmp_x;
		board.position_y[Source_index]=tmp_y;
		board.position_x[Dest_index]=extra_x;
		board.position_y[Dest_index]=extra_y;

		//이미지 바꾸기 
		Image Buffer_img = board.images[Dest_index];
		board.images[Dest_index]=board.images[Source_index];
		board.images[Source_index]=Buffer_img;

		//board.repaint();
	}
}
