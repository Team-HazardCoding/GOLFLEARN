package com.golflearn.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.golflearn.mail.Smtp;
import com.golflearn.sql.MyConnection;

@WebServlet("/findpwd")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		String userEmail = request.getParameter("user_email");
		

		//SQL 송신
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = MyConnection.getConnection();

			String selectEmailSQL = "SELECT user_id, user_email FROM user_info WHERE user_id=? AND user_email=?";
			pstmt = con.prepareStatement(selectEmailSQL);
			pstmt.setString(1, userId);
			pstmt.setString(2, userEmail);
			rs=pstmt.executeQuery();
			String id = null;
			String email = null;
			if(rs.next()) {
				id = rs.getString("user_id");
				email = rs.getString("user_email");
			}

			//사용자가 입력하지 않은 경우
			//front에서 required 처리해줘도 상관없음
			if(userId == null || userEmail == null){
				request.setAttribute("msg", "아이디나 이메일 정보가 누락됨");
				request.setAttribute("loc", "/findpwd");
				request.getRequestDispatcher("/findpwd").forward(request, response);
				return;

			//사용자가 정확한 값을 넣은 경우
			} else if (id.equals(userId) && email.equals(userEmail)) {
				Smtp smtp = new Smtp();
				smtp.gmailSend(request, response, userId, userEmail);	
				return;
			//사용자가 값을 하나라도 잘못 입력한 경우
			} else {
				request.setAttribute("msg", "아이디나 이메일이 일치하지 않습니다");
				request.setAttribute("loc", "/findpwd");
				request.getRequestDispatcher("/findpwd").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}