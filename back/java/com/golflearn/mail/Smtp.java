package com.golflearn.mail;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Smtp {
	
	public static String gmailSend(HttpServletRequest request, HttpServletResponse response, String userId, String userEmail, String AuthenticationKey) {
        String user = "golflearn1124@gmail.com"; // gmail 계정
        String password = "jnwchtzqlhsxnzdb";   // gmail 패스워드
        
        String toEmail = userEmail;

        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", 465); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.ssl.enable", "true"); 
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.debug", "true");
        
		//난수 발생
		//문자열추가
		StringBuffer bs = new StringBuffer();
		Random rnd = new Random();
		for(int i=0; i<10; i++) {
			int rndIndex = rnd.nextInt(3);
			switch(rndIndex) {
			case 0 :
				//char 97(영어소문자 a-z)
				bs.append((char)((int)(rnd.nextInt(26)) + 97));
			case 1 :
				//숫자 0-9
				bs.append((rnd.nextInt(10)));
			case 2 :
				//char 65 (영어 대문자 A-Z)
				bs.append((char)((int)(rnd.nextInt(26)) + 65));
				break;
			}
		}
			//문자열 리턴
			AuthenticationKey = bs.toString();
//			System.out.println(AuthenticationKey);
        
		//Session 클래스의 getDefaultInstance() 메소드는 파라미터로 전달받은 Properties에 저장되어 있는 속성값을 사용하여 세션을 생성
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); 

            //메일 제목 입력
            message.setSubject("Golflearn 비밀번호 인증코드 안내 메일입니다 ");

            //메일 내용 입력
            message.setText("인증코드 : " + bs);

            //메일 전송
            Transport.send(message);
            System.out.println("메일이 성공적으로 발송되었습니다");
            System.out.println(AuthenticationKey);
            
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		return AuthenticationKey;
    }
}
//		HttpSession saveKey = request.getSession();
//		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
//		request.setAttribute("id", userId);