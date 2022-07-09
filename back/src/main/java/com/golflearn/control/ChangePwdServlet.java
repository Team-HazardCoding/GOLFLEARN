package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.sql.MyConnection;

@WebServlet("/changepwd")
public class ChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에 저장되어있는 값 가져오기
		//세션에 저장되어있는 인증코드를 스트링 타입으로 가져옴(getattribute는 object타입으로 반환되어져서 string으로 형변환필요)
		String authenticationKey = (String)request.getSession().getAttribute("Authenticationkey");
		//세션에 저장되어있는 아이디 정보를 가져옴
		String id = (String)request.getSession().getAttribute("id");
		
		//test
//		String testKey = "abcde";
//		String testId = request.getParameter("id");
		
		
		//입력창에서 값 받아오기
		//AuthenticationUser값을 받아온다
		String authenticationUser = request.getParameter("authenticationUser");
		//user_pwd값을 받아온다
		String newPwd = request.getParameter("user_pwd1");
		String chkNewPwd = request.getParameter("user_pwd2");
		
		String updatePwdResult = "{\"status\":0, \"msg\": \"비밀번호 변경 실패\"}";

		//AuthenticationKey와 AuthenticationUser이 다를 경우
//		if(! authenticationKey.equals(authenticationUser)){
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(! authenticationKey.equals(authenticationUser)){
			System.out.println("인증번호 일치하지 않음");
			out.println("인증번호 일치하지 않음");
			request.setAttribute("msg", "인증번호가 일치하지 않습니다");
			//request.setAttribute("loc", "");
		}else {
			if(newPwd.equals(chkNewPwd)) {
				Connection con = null;
				PreparedStatement pstmt = null;
				int rs = 0;
				
				try {
					con = MyConnection.getConnection();
					String UpdPwdSQL = "UPDATE user_info SET user_pwd = ? WHERE user_id = ?";
					pstmt = con.prepareStatement(UpdPwdSQL);
					pstmt.setString(1, newPwd);
					pstmt.setString(2, id);
					rs = pstmt.executeUpdate();
					if(rs == 1) {
						updatePwdResult = "{\"status\":1, \"msg\": \"비밀번호 변경 성공\"}";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					MyConnection.close(pstmt, con);
				}
				out.print(updatePwdResult);
			}else {
				System.out.println("비밀번호 일치하지 않음");
				out.print("password x");
				request.setAttribute("msg", "비밀번호가 일치하지 않습니다");
				//request.setAttribute("loc", "프론트");
			}
		}
	}
}
