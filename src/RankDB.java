import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankDB {
	
	public void insertDB(String name,int score){
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		String url = "jdbc:mysql://117.17.142.207:3306/test_kim";
		String user = "root";
		String pw = "cs616";
		String sql = "insert into fcm(name,score) values(?,?)";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url,user,pw);
				pst = conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, score);
				pst.executeUpdate();
				System.out.println("insert Success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	
	public ArrayList<RankVO> selectRank(){
		RankVO vo;
		ArrayList<RankVO> list = new ArrayList<RankVO>();
		
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		String url = "jdbc:mysql://117.17.142.207:3306/test_kim";
		String user = "root";
		String pw = "cs616";
		String sql = "select name,score from fcm order by score desc limit 5";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
				vo = new RankVO();
				vo.setName(rs.getString(1));
				vo.setScore(rs.getInt(2));
				list.add(vo);
				
			}
			System.out.println("select Success");
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
