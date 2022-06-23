package health_up1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DB {
	private Connection conn;
	private Statement stmt;
	public Connection getConn() {
		return conn;
	}
	public DB() {
		try {
			String url="jdbc:mariadb://localhost:3306/health_up";
			String id="root";
			String pwd="1234";
			Class.forName("org.mariadb.jdbc.Driver");
			conn=DriverManager.getConnection(url, id, pwd);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean registerMember(Member member) {
		String query="insert into data1 values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, member.getNum());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getId());
			pstmt.setString(4, member.getPass());
			pstmt.setString(5, member.getTel());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("회원가입 실패:"+e);
			
			return false;
		}
		
	}
	public int loginMember(Member member) {
		String query="SELECT * FROM data1 WHERE id=? AND pw=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			
			ResultSet rs=pstmt.executeQuery();
			
			if(!rs.next()) {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
				System.out.println("로그인 실패.");
			}
			
			int result1= rs.getInt(1);

			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
			return result1;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("로그인 실패!");
			return 0;
		}
	}
	
	public boolean deleteMember(String id) {
		String query="delete from data1 where id=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
			
		} catch (SQLException e) {
			System.out.println("회원삭제 실패");
			return false;
		}
		
		return true;
	}
	
	public boolean editMember(Member member) {
		String query="update data1 set num=?,name=?,pw=?,tel=? where id=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,member.getNum());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPass());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getId());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
			
		} catch (SQLException e) {
			System.out.println("회원수정 실패");
			return false;
		}
		
		return true;
	}
	
	public Statement getStatement() {
		try {
			stmt=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	
	


}
