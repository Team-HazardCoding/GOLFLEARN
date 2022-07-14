package com.golflearn.control;

import java.io.File;
import java.util.List;

public class DirectoryTest {

	public static void main(String[] args) {
		File file = new File(".");
		String abPath = file.getAbsolutePath();
		System.out.println("절대경로 1" + abPath);

		String rootPath = file.getAbsolutePath();
		System.out.println("절대경로 2" + rootPath);

		String[] list = file.list();
		for(int i=0; i<list.length;i++) {
			System.out.println(list[i]);
		}
	}
}

