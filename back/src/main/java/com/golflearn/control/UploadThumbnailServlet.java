package com.golflearn.control;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadthumbnail")
public class UploadThumbnailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폴더명 생성을 위해 lsn_no 값을 가져옴
		String lsnNo = request.getParameter("lsn_no");
		System.out.println(lsnNo);

		//디렉토리에 파일 생성
		request.setCharacterEncoding("UTF-8");
		String uploadPathName= "C:\\Golflearn_lib\\user_images\\" + lsnNo ; //파일 업로드 할 절대경로 지정 
																			//gitignore를 사용해서 폴더를 따로 지정할 수 있지만
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
