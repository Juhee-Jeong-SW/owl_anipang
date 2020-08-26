import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GameBoard extends JPanel{

	final static int CellWidth = 80; //�̹��� ������
	final static int CellHeight = 80;
	final static int CellCount = 7;	//����,���� �̹����� ����.
	
	//���� �߻��� ���� Random Ÿ�� ��ü ����.
	Random rand = new Random();
	
	//MouseClick �����ʿ��� ���� 
	//Click_index�� source ��  ����
	//Up_index�� dest �� ����.
	public int source_index,dest_index;
	
	public int Score;
	
	Image[] images = new Image[CellCount*CellCount];
	String[] paths = {"image1.png","image2.png","image3.png","image4.png","image5.png","image6.png","image7.png"};
	
	int[] position_x = new int[CellCount*CellCount];
	int[] position_y = new int[CellCount*CellCount];
	
	Graphics g;
	
	//�� �̹������� ���� �̹���, �̹����� �׷��� x,y��ǥ �ʱ�ȭ.
	public void initialize()
	{
		for(int i =0;i<CellCount*CellCount;i++)
		{
			//7*7���� ���� �̹��� �迭 ����
			images[i] = getToolkit().getImage(paths[rand.nextInt(7)]);
			
			//�� ������ x,y��ǥ
			position_x[i]=i%CellCount*CellWidth;
			position_y[i]=i/CellCount*CellHeight;
		}
		
		
		
		//find() �� ������ �ִϸ��̼� ȿ�� ���� ver.
		//for ������ �ӽ������� 2������ �ذ�
		for(int i=0;i<CellCount;i++)
		{
		Search();
		for(int row =0;row<CellCount;row++)
		{
			for(int col=0;col<CellCount;col++)
			{
				
				if(images[row*CellCount+col]==getToolkit().getImage("image8.png"))
				{					
					
					for(int inner_row=row; inner_row>0; inner_row--) //�̰� ������ ������ 
					{   
						images[(inner_row)*CellCount+col]=images[(inner_row-1)*CellCount+col];
					}
					System.out.println(col+"����");
					
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
				
				//���η� 3��������� ã�� �� getToolkit().getImage("image8.png") �� ��ȯ and ������  T���� �� 5���� ������� ã���� getToolkit().getImage("image8.png") �� ��ȯ.
				if(images[row*CellCount+col]==images[row*CellCount+col-1]&&images[row*CellCount+col]==images[row*CellCount+col+1])
				{
					buffer_img=images[row*CellCount+col];
					images[(row)*CellCount+col-1]=getToolkit().getImage("image8.png");
					images[(row)*CellCount+col]=getToolkit().getImage("image8.png");
					images[(row)*CellCount+col+1]=getToolkit().getImage("image8.png");
	       
					Score+=30;
					//3�� ã���� �ϳ� �� ������ 
					if(col+2<= CellCount-1 &&images[(row)*CellCount+col+2]==buffer_img)//���� 4�� ����� 
					{
						images[(row)*CellCount+col+2]=getToolkit().getImage("image8.png");
						//���ھ� +
						Score+=10;
						//4�� �°� �ϳ��� ������
						if(col+3<= CellCount-1 &&images[row*CellCount+col+3]==buffer_img)//���� 5�� ����� 
						{
							images[(row)*CellCount+col+3]=getToolkit().getImage("image8.png");
							Score+=10;
						}
					}
					
					for(int inner_col=col-1;inner_col<col+2;inner_col++)
					{
						//row 0������~4���ο� ���հ��̸� row*CellCount,(row+1)*CellCount,(row+2)*CellCount�� �̹����� ������ �˻�.
						if(row>=0&&row<=CellCount-2) //3�� ¥��
						{
							//�� �� r ��� 
							if(buffer_img==images[(row+1)*CellCount+inner_col]&&buffer_img==images[(row+2)*CellCount+inner_col])
							{   //1�� ���� 
								for(int inner_row=row;inner_row>row-1;inner_row--)
								{
									images[(row+1)*CellCount+inner_col]=getToolkit().getImage("image8.png");
									images[(row+2)*CellCount+inner_col]=getToolkit().getImage("image8.png");
									buffer_img=getToolkit().getImage("image8.png");
	           
									Score+=20;
								}
								//������
								images[row*CellCount+inner_col]=getToolkit().getImage("image9.png");
							}
	         
						}
						// row 1~5 ���� �� ,��,+��� 
						if(row>=1&&row<=CellCount-2)//3��¥��
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
								// ������
								images[row*CellCount+inner_col]=getToolkit().getImage("image9.png");
							}	         
						}
						// row 2~6 ���� �� �� �� ��  ��
						if(row>=2&&row<=CellCount-1) //3��¥��
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
		
	    //���η� 3�� ������� ã���� getToolkit().getImage("image8.png")�� ��ȯ.
	    for(int col=0;col<CellCount;col++) 
	    {
	    	for(int row=1;row<CellCount-1;row++)
	    	{
	    		// row , col �������� ��,�Ʒ� ������ Ȯ��
	    		if(images[row*CellCount+col]==images[(row+1)*CellCount+col]&&images[row*CellCount+col]==
	    				images[(row-1)*CellCount+col])
	    		{ 
	    			//���� ���� �Ѿ�� �ʰ� ������
	    			if(row+2<=CellCount-1&&images[(row+2)*CellCount+col]==images[row*CellCount+col])//���� 4�� ����� 
	    			{
	    				images[(row+2)*CellCount+col]=getToolkit().getImage("image8.png");
	          
	    				if(row+3<=CellCount-1&&images[(row+3)*CellCount+col]==images[row*CellCount+col])//���� 5�� ����� 
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
	     
	    	for(int row =0;row<CellCount;row++)//���� 
	    	{
	    		for(int col=1;col<CellCount-1;col++)
	    		{
	    			if(images[Click_index]==images[row*CellCount+col]&&images[row*CellCount+col]==images[row*CellCount+col-1]&&images[row*CellCount+col]==images[row*CellCount+col+1])
	    			{
	           
	    				System.out.print("ã��\n");
	    				return false;
	    			}
	    			if(images[Up_index]==images[row*CellCount+col]&&images[row*CellCount+col]==images[row*CellCount+col-1]&&images[row*CellCount+col]==images[row*CellCount+col+1])
	    			{
	           
	    				System.out.print("ã��\n");
	    				return false;
	    			}
	    		}
	    	}
	     
	    	for(int col=0;col<CellCount;col++) //����
	    	{
	    		for(int row=1;row<CellCount-1;row++)
	    		{
	    			try
	    			{ Thread.sleep(5); }catch(InterruptedException ex){}
	    			if(images[Click_index]==images[row*CellCount+col]&&images[row*CellCount+col]==images[(row+1)*CellCount+col]&&images[row*CellCount+col]==images[(row-1)*CellCount+col])
	    			{
	       
	    				System.out.print("ã��\n");
	    				return false;
	    			}
	    			if(images[Up_index]==images[row*CellCount+col]&&images[row*CellCount+col]==images[(row+1)*CellCount+col]&&images[row*CellCount+col]==images[(row-1)*CellCount+col])
	    			{try
	    			{ Thread.sleep(5);	}catch(InterruptedException ex){}
	        
	    			System.out.print("ã��\n");
	    			return false;
	    			}
	    		}
	    	}
	     
	   
	     if(RT)
	     {
	    	 System.out.print("�´� �� ���� \n");
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
	



