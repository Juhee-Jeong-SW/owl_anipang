
public class Clock extends Thread{

	GameBoard board;
	Status state;
	
	public boolean run =false;
	 
	public Clock(GameBoard board,Status state)
	{
		this.state = state;
		this.board = board;
		state.Counter =20;
	}

	
	//Counter = 타이머  
	@Override
	public void run() {
		run = true;
		
		//시간초가 0 이상일때 루프 돈다 
		while(state.Counter>0)
		{
			//0.5 초 동안 Thread.sleep
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();//에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
			}

			//그림들이 안겹치게 이미지를 자신들의 row 와 col 위치로 이동시킴.
			for(int i=0;i<GameBoard.CellCount*GameBoard.CellCount;i++)
			{
				board.position_x[i]=i%GameBoard.CellCount*GameBoard.CellWidth;
				board.position_y[i]=i/GameBoard.CellCount*GameBoard.CellHeight;
			}
			
			//board 의 search 와 repaint 메소드 호출 
			board.Search();
			board.repaint();
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//새로운 셀 내려옴 
			board.down();
			
			System.out.println(state.Counter);

			//시간 출력.
			state.repaint();
			
			
			state.Counter--;
		}
		
		//시간 끝났을때 모든 이미지 없앰
		run = false;		 
		for(int i =0;i<GameBoard.CellCount*GameBoard.CellCount;i++)
			board.images[i]=null;
		
		 
		GameOver restart = new GameOver(board.Score);
		System.out.println(board.Score);
		 
		board.repaint();
	}
	 
}
