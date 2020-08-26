import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOver extends JPanel {
	RankDB db = new RankDB();
	GameBoard board = new GameBoard();
	Rank rank;
	String _score;
	int score;
	
	public GameOver(int Score){
		JFrame frame = new JFrame();

		this.score = Score;
		frame.setLocation(100,10);
		Container contentPane = frame.getContentPane();


		this.setPreferredSize(new Dimension(700,422));
		this.setLayout(null);
		contentPane.add(this);


		final JTextField name = new JTextField(10);
		name.setBounds(200,	286, 300, 38);

		final JButton reBtn = new JButton(new ImageIcon("reStartGame.png"));             
		reBtn.setBounds(20,320,164,70);

		JButton rankBtn = new JButton(new ImageIcon("Ranking.png"));
		rankBtn.setBounds(516,323,164,70);

		rankBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n =name.getText();
				System.out.println(n);
				db.insertDB(n, score);
				rank = new Rank();
			}
		});




		this.add(name,BorderLayout.NORTH);
		this.add(reBtn,BorderLayout.CENTER);
		this.add(rankBtn,BorderLayout.CENTER);

		 
		StartClick reStartClick = new StartClick(reBtn,contentPane,frame,rankBtn,name);
		reBtn.addMouseListener(reStartClick);

		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close 버튼의 이벤트 핸들러 추가
		frame.pack();
		frame.setVisible(true);
	}
	

	Image img = getToolkit().getImage("overBackGround.png");

	public void paint(Graphics g)
	{
		_score = Integer.toString(score);
		g.drawImage(img,0,0,this);
		g.setFont(new Font("SansSerif", Font.BOLD, 100));
		g.setColor(new Color(0,0,0));
		g.drawString(_score, 363, 160);
		 
	}




}