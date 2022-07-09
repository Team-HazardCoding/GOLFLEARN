package com.golflearn.control;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadcertf")
@MultipartConfig // multipart를 인식하기 위해 설정하는 것
// 하기와 같이 속성 지정 가능
// @MultipartConfig( fileSizeThreshold = 0, maxFileSize = 209715200, maxRequestSize = 209715200)
// fileSizeThreshold : 파일이 디스트에 임시로 저장될 파일 크기(바이트)
// MaxFileSize : 업로드된 파일에 허용되는 최대 크키(바이트) 업로드된 파일의 크기가 이 크기보다 크면 예외 발생. 기본 크기 무제한
// maxRequestSize : multipart/form-data 요청에 허용되는 최대크기(바이트). 업로드된 모든 파일의 전체 크기가 이 값을 초과하면 예외 발생

public class UploadCertfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폴더명 생성을 위해 user_id 값을 가져옴
		String userId = request.getParameter("user_id");
		System.out.println(userId);

		//디렉토리에 파일 생성
		request.setCharacterEncoding("UTF-8");
		String uploadPathName= "C:\\Golflearn_lib\\user_images\\" + userId ; // 파일 업로드 할 절대경로 지정 
		// uploadPathName에 해당되는 File 객체 생성
		File uploadDirectory = new File(uploadPathName);

		try {
			if(!uploadDirectory.exists()) {
				System.out.println("업로드 실제 경로 :" + uploadPathName);
				uploadDirectory.mkdirs(); // 디렉토리에 폴더가 없을 때 생성해 줌(user_images)
				//mkdir() : 만들고자하는 폴더의 상위 폴더(디렉토리)가 존재하지 않을 경우 폴더 생성 X 
				//mkdirs() : 해당 디렉토리에 상위 폴더가 없으면 폴더부터 생성해 줌

			}
			// 파일 절대 경로 확인
			System.out.println("파일의 절대 경로는 :" + uploadDirectory.getAbsolutePath());

			// request.getParameter()를 이용해 값을 가져온 것 > 파일은 part로 받아와야한다.
			// front에서 파일 업로드 위해 formData 객체가 필요함 why? just 문법
			Collection<Part> parts = request.getParts(); // 요청시 전달된 formData를 얻는 API // type이 Collection<part>
			System.out.println(parts); // [org.apache.catalina.core.ApplicationPart@7086c56b]

			
			for(Part part: parts) {
				String paramName = part.getName(); //part의 getName() 요청 시 전달된 key값이 됨
				System.out.println("넘어온 파라미터값 : " + paramName); //part.getName() - 넘어온 파라미터값
				System.out.println("업로드한 파일명 : " + part.getSubmittedFileName()); //part.getSubmittedFileName() - 업로드한 파일의 이름
				System.out.println("업로드한 파일의 크기 : " + part.getSize()); //part.size() - 파일의 크기

				String originFileName = part.getSubmittedFileName();
				String fileExtension = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("파일 확장자 : " + fileExtension); // .pdf로 나옴 lastIndextOf(".")+1 -> pdf로 출력

				String fileName = "Certification";
				System.out.println(fileName);
				part.write(uploadDirectory.getAbsolutePath()+"\\"+fileName + fileExtension); // 파일 확장자 포함하여 저장되도록
			}
		}catch (Exception e) {
			e.printStackTrace();
		}


	}
}
