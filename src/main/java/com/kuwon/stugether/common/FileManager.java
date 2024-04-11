package com.kuwon.stugether.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	public final static String FILE_UPLOAD_PATH = "C:\\Users\\JW K\\Desktop\\megaIT\\springProject\\upload\\stugether";
	
	// summernote 이미지 작성 시 임시 파일 저장
	public static String saveTempFile(MultipartFile file, int userId) {
		if(file == null) {
			return null;
		}
		String directoryName = "/temp/" + userId;
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(directoryPath);
		if(!directory.exists()) {
			if(!directory.mkdir()) {
				return null;
			}
		}
		
		
		// 업로드한 파일명과 해당 파일의 확장자 추출
		String originalFileName = file.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID() + extension;
		// 고유 식별자를 파일 이름으로 지정
		String filePath = directoryPath + "/" + newFileName;
		try {
			byte[] bytes = file.getBytes(); // 파일을 바이트 단위로 객체에 저장
			Path path = Paths.get(filePath); // 파일명 포함 경로 가져오기
			Files.write(path, bytes);        // 해당 경로로 파일 저장
		}catch (IOException e) {
			// 파일 저장 실패 시
			e.printStackTrace();
			directory.delete(); // 생성했던 디렉토리 삭제
			return null;
		}
		
		return "/images" + directoryName + "/" + newFileName;
	}

	// summernote에 작성된 임시 이미지 파일 삭제
	public static void deleteTempImage(String fileName, int userId) {
		String filePath = FILE_UPLOAD_PATH + "/temp/" + userId + "/" + fileName;
		Path path = Paths.get(filePath);
		try {
			Files.delete(path);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
