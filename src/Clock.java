
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

	
	//Counter = Ÿ�̸�  
	@Override
	public void run() {
		run = true;
		
		//�ð��ʰ� 0 �̻��϶� ���� ���� 
		while(state.Counter>0)
		{
			//0.5 �� ���� Thread.sleep
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();//���� �޼����� �߻� �ٿ����� ã�Ƽ� �ܰ躰�� ������ ���
			}

			//�׸����� �Ȱ�ġ�� �̹����� �ڽŵ��� row �� col ��ġ�� �̵���Ŵ.
			for(int i=0;i<GameBoard.CellCount*GameBoard.CellCount;i++)
			{
				board.position_x[i]=i%GameBoard.CellCount*GameBoard.CellWidth;
				board.position_y[i]=i/GameBoard.CellCount*GameBoard.CellHeight;
			}
			
			//board �� search �� repaint �޼ҵ� ȣ�� 
			board.Search();
			board.repaint();
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//���ο� �� ������ 
			board.down();
			
			System.out.println(state.Counter);

			//�ð� ���.
			state.repaint();
			
			
			state.Counter--;
		}
		
		//�ð� �������� ��� �̹��� ����
		run = false;		 
		for(int i =0;i<GameBoard.CellCount*GameBoard.CellCount;i++)
			board.images[i]=null;
		
		 
		GameOver restart = new GameOver(board.Score);
		System.out.println(board.Score);
		 
		board.repaint();
	}
	 
}
