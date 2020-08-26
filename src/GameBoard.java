import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GameBoard extends JPanel{

	final static int CellWidth = 80; //이미지 사이즈
	final static int CellHeight = 80;
	final static int CellCount = 7;	//가로,세로 이미지의 갯수.
	
	//난수 발생을 위한 Random 타입 객체 선언.
	Random rand = new Random();
	
	//MouseClick 리스너에서 받은 
	//Click_index는 source 로  전달
	//Up_index는 dest 로 전달.
	public int source_index,dest_index;
	
	public int Score;
	
	Image[] images = new Image[CellCount*CellCount];
	String[] paths = {"image1.png","image2.png","image3.png","image4.png","image5.png","image6.png","image7.png"};
	
	int[] position_x = new int[CellCount*CellCount];
	int[] position_y = new int[CellCount*CellCount];
	
	Graphics g;
	
	//각 이미지들의 실제 이미지, 이미지가 그려질 x,y좌표 초기화.
	public void initialize()
	{
		for(int i =0;i<CellCount*CellCount;i++)
		{
			//7*7개의 랜덤 이미지 배열 생성
			images[i] = getToolkit().getImage(paths[rand.nextInt(7)]);
			
			//각 셀들의 x,y좌표
			position_x[i]=i%CellCount*CellWidth;
			position_y[i]=i/CellCount*CellHeight;
		}
		
		
		
		//find() 와 같지만 애니메이션 효과 없는 ver.
		//for 문으로 임시적으로 2번문제 해결
		for(int i=0;i<CellCount;i++)
		{
		Search();
		for(int row =0;row<CellCount;row++)
		{
			for(int col=0;col<CellCount;col++)
			{
				
				if(images[row*CellCount+col]==getToolkit().getImage("image8.png"))
				{					
					
					for(int inner_row=row; inner_row>0; inner_row--) //이게 밑으로 내리기 
					{   
						images[(inner_row)*CellCount+col]=images[(inner_row-1)*CellCount+col];
					}
					System.out.println(col+"생성");
					
					images[col]=getToolkit().getImage(paths[rand.nextInt(7)]);					
					
				}
			}
		}
		}
		repaint();
		Score=0;
	}
	
	public void paint(Graphics g)
	{
		this.g=g;
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(getToolkit().getImage("background.png"),0,0,this);
		for(int row =0; row<CellCount;row++)
		{
			for(int col =0; col<CellCount;col++)
			{
				int index=row*CellCount+col;
				if(images[index] !=getToolkit().getImage("image8.png"))
					g.drawImage(images[index],position_x[index],position_y[index],this);
			}
		}
	}
	
	public void Search()
	{
		Image buffer_img=getToolkit().getImage("image8.png");
	    
		for(int row =0;row<CellCount;row++)
	    {
			for(int col=1;col<CellCount-1;col++)
			{
				if(images[row*CellCount+0]==images[row*CellCount+1]&&images[row*CellCount+0]==images[row*CellCount+2]&&
						images[row*CellCount+0]==images[row*CellCount+3]&&images[row*CellCount+0]==images[row*CellCount+4]&&
						images[row*CellCount+0]==images[row*CellCount+5])
				      break;
				
				//가로로 3개같은경우 찾은 후 getToolkit().getImage("image8.png") 로 변환 and ㄱ형태  T형태 로 5개가 같은경우 찾은후 getToolkit().getImage("image8.png") 로 변환.
				if(images[row*CellCount+col]==images[row*CellCount+col-1]&&images[row*CellCount+col]==images[row*CellCount+col+1])
				{
					buffer_img=images[row*CellCount+col];
					images[(row)*CellCount+col-1]=getToolkit().getImage("image8.png");
					images[(row)*CellCount+col]=getToolkit().getImage("image8.png");
					images[(row)*CellCount+col+1]=getToolkit().getImage("image8.png");
	       
					Score+=30;
					//3개 찾고난후 하나 더 맞을시 
					if(col+2<= CellCount-1 &&images[(row)*CellCount+col+2]==buffer_img)//가로 4개 만들기 
					{
						images[(row)*CellCount+col+2]=getToolkit().getImage("image8.png");
						//스코어 +
						Score+=10;
						//4개 맞고 하나더 맞을시
						if(col+3<= CellCount-1 &&images[row*CellCount+col+3]==buffer_img)//가로 5개 만들기 
						{
							images[(row)*CellCount+col+3]=getToolkit().getImage("image8.png");
							Score+=10;
						}
					}
					
					for(int inner_col=col-1;inner_col<col+2;inner_col++)
					{
						//row 0번부터~4번로우 사잇값이면 row*CellCount,(row+1)*CellCount,(row+2)*CellCount의 이미지가 같은지 검사.
						if(row>=0&&row<=CellCount-2) //3개 짜리
						{
							//ㄱ 자 r 모양 
							if(buffer_img==images[(row+1)*CellCount+inner_col]&&buffer_img==images[(row+2)*CellCount+inner_col])
							{   //1번 돌기 
								for(int inner_row=row;inner_row>row-1;inner_row--)
								{
									images[(row+1)*CellCount+inner_col]=getToolkit().getImage("image8.png");
									images[(row+2)*CellCount+inner_col]=getToolkit().getImage("image8.png");
									buffer_img=getToolkit().getImage("image8.png");
	           
									Score+=20;
								}
								//아이템
								images[row*CellCount+inner_col]=getToolkit().getImage("image9.png");
							}
	         
						}
						// row 1~5 사이 ㅏ ,ㅓ,+모양 
						if(row>=1&&row<=CellCount-2)//3개짜리
						{
							if(buffer_img==images[(row-1)*CellCount+inner_col]&&buffer_img==images[(row+1)*CellCount+inner_col])
							{
								for(int inner_row=row;inner_row>row-1;inner_row--)
								{
									images[(row-1)*CellCount+inner_col]=getToolkit().getImage("image8.png");
									images[(row+1)*CellCount+inner_col]=getToolkit().getImage("image8.png");
									buffer_img=getToolkit().getImage("image8.png");
	           
									Score+=20;
								}
								// 아이템
								images[row*CellCount+inner_col]=getToolkit().getImage("image9.png");
							}	         
						}
						// row 2~6 사이 ㄴ 자 ┙ 자  ㅗ
						if(row>=2&&row<=CellCount-1) //3개짜리
						{
							if(buffer_img==images[(row-2)*CellCount+inner_col]&&buffer_img==images[(row-1)*CellCount+inner_col])
							{
								for(int inner_row=row;inner_row>row-1;inner_row--)
								{
									images[(row-2)*CellCount+inner_col]=getToolkit().getImage("image8.png");
									images[(row-1)*CellCount+inner_col]=getToolkit().getImage("image8.png");
									buffer_img=getToolkit().getImage("image8.png");
	           
									Score+=20;
								}
								images[row*CellCount+inner_col]=getToolkit().getImage("image9.png");
							}
						}       
					}    
				} 
			}  
	    }
		
	    //세로로 3개 붙은경우 찾은후 getToolkit().getImage("image8.png")로 변환.
	    for(int col=0;col<CellCount;col++) 
	    {
	    	for(int row=1;row<CellCount-1;row++)
	    	{
	    		// row , col 기준으로 위,아래 같은지 확인
	    		if(images[row*CellCount+col]==images[(row+1)*CellCount+col]&&images[row*CellCount+col]==
	    				images[(row-1)*CellCount+col])
	    		{ 
	    			//다음 열로 넘어가지 않고 같을때
	    			if(row+2<=CellCount-1&&images[(row+2)*CellCount+col]==images[row*CellCount+col])//세로 4개 만들기 
	    			{
	    				images[(row+2)*CellCount+col]=getToolkit().getImage("image8.png");
	          
	    				if(row+3<=CellCount-1&&images[(row+3)*CellCount+col]==images[row*CellCount+col])//세로 5개 만들기 
	    				{
	    					images[(row+3)*CellCount+col]=getToolkit().getImage("image8.png");
	    				}
	    			}
	    			images[row*CellCount+col]=getToolkit().getImage("image8.png");
	    			images[(row+1)*CellCount+col]=getToolkit().getImage("image8.png");
	    			images[(row-1)*CellCount+col]=getToolkit().getImage("image8.png");
	       
	    			Score+=30;
	       
	    		}
	      
	    	}
	    }
	    
	}

	
		Animation ani;

		
		public void down()
		{
			for(int row =CellCount-1;row>=0;row--)
			{
				for(int col=CellCount-1;col>=0;col--)
				{
				
					if(images[row*CellCount+col]==getToolkit().getImage("image8.png") && row!=0)
					{ 
						ani = new Animation(this,(row-1)*CellCount+col,row*CellCount+col);
						ani.start();
					}
					if(images[row*CellCount+col]==getToolkit().getImage("image8.png") && row==0)
					{
						images[0*CellCount+col]=getToolkit().getImage(paths[rand.nextInt(7)]);
					}
	   
	   
				}   repaint();
			}
		}
		

		boolean RT = true;
	    public boolean Match(int Click_index,int Up_index)
	    {	     
	    	RT=true;
	     
	    	for(int row =0;row<CellCount;row++)//가로 
	    	{
	    		for(int col=1;col<CellCount-1;col++)
	    		{
	    			if(images[Click_index]==images[row*CellCount+col]&&images[row*CellCount+col]==images[row*CellCount+col-1]&&images[row*CellCount+col]==images[row*CellCount+col+1])
	    			{
	           
	    				System.out.print("찾음\n");
	    				return false;
	    			}
	    			if(images[Up_index]==images[row*CellCount+col]&&images[row*CellCount+col]==images[row*CellCount+col-1]&&images[row*CellCount+col]==images[row*CellCount+col+1])
	    			{
	           
	    				System.out.print("찾음\n");
	    				return false;
	    			}
	    		}
	    	}
	     
	    	for(int col=0;col<CellCount;col++) //세로
	    	{
	    		for(int row=1;row<CellCount-1;row++)
	    		{
	    			try
	    			{ Thread.sleep(5); }catch(InterruptedException ex){}
	    			if(images[Click_index]==images[row*CellCount+col]&&images[row*CellCount+col]==images[(row+1)*CellCount+col]&&images[row*CellCount+col]==images[(row-1)*CellCount+col])
	    			{
	       
	    				System.out.print("찾음\n");
	    				return false;
	    			}
	    			if(images[Up_index]==images[row*CellCount+col]&&images[row*CellCount+col]==images[(row+1)*CellCount+col]&&images[row*CellCount+col]==images[(row-1)*CellCount+col])
	    			{try
	    			{ Thread.sleep(5);	}catch(InterruptedException ex){}
	        
	    			System.out.print("찾음\n");
	    			return false;
	    			}
	    		}
	    	}
	     
	   
	     if(RT)
	     {
	    	 System.out.print("맞는 것 없음 \n");
	     }
	     try{ Thread.sleep(5); }catch(InterruptedException ex){}
	    
	     return true;
	     }
	    
	    public void Boom(int index)
	    {
	    	int col,row;
	     
	    	row=index/CellCount;
	    	col=index%CellCount;
	     
	    	for(int row1=0;row1<CellCount;row1++)
	    	{
	    		images[row1*CellCount+col]=getToolkit().getImage("image8.png");
	    	}
	     
	    	for(int col1=0;col1<CellCount;col1++)
	    	{
	    		images[row*CellCount+col1]=getToolkit().getImage("image8.png");
	    	}
	    	images[index]=getToolkit().getImage("image8.png");
	    }
	    
	     
}
	



