package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMembers() {
		
		List<MemberVO> membersList = new ArrayList<MemberVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select & from t_member order by joinDate desc"; //SQL문
			System.out.println(query);
			pstmt  = conn.prepareStatement(query); //prepareStatement 객체를 생성하면서 SQL문을 인자로 전달
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate); //조회한 회원정보를 레코드별로 VO객체 속성에 저장
				membersList.add(memberVO); //memberList에 MemeberVO 객체들을 차례로 저장(add)
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}

	
	
	public void addMember(MemberVO m) {
		try {
			conn = dataFactory.getConnection();
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "insert into t_member(id, pwd, name, email)" + " values(?,?,?,?)"; //쿼리문 이용해서 t_member에 정보등록
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); //prepareStatement 객체를 생성하면서 SQL문을 인자로 전달
			pstmt.setString(1,  id);
			pstmt.setString(2,  pwd);
			pstmt.setString(3,  name);
			pstmt.setString(4,  email);
			
			pstmt.executeUpdate(); //SQL문을 실행
			pstmt.close();
			conn.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	
}//class end
