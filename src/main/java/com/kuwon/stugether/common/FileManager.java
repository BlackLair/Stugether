package com.kuwon.stugether.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	public final static String FILE_UPLOAD_PATH = "C:\\Users\\JW K\\Desktop\\megaIT\\springProject\\upload\\stugether";
	public final static String TYPE_BLOG = "blog";
	public final static String TYPE_GROUP = "group";
	public final static String TYPE_PROBLEM = "problem";
	public final static String TYPE_QUESTION = "question";
	
	// summernote 이미지 작성 시 임시 파일 저장
	public static String saveTempImage(MultipartFile file, int userId, String editorToken) {
		if(file == null) {
			return null;
		}
		String directoryName = "/temp/" + userId + "_" + editorToken;
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
	public static void deleteTempImage(String fileName, int userId, String editorToken) {
		String filePath = FILE_UPLOAD_PATH + "/temp/" + userId + "_" + editorToken + "/" + fileName;
		Path path = Paths.get(filePath);
		try {
			Files.delete(path);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 업로드된 게시물 사진 저장
	public static String saveImage(int userId, String type, String currentTime, String editorToken) {
		String tempPath = FILE_UPLOAD_PATH + "/temp/" + userId + "_" + editorToken + "/"; // 임시 이미지 파일 경로
		String targetPath = FILE_UPLOAD_PATH + "/" + type + "/" + userId + "_" + currentTime + "/"; // 이미지가 저장될 경로
		File folder1 = new File(tempPath);
		File folder2 = new File(targetPath);
		if(!folder1.exists()) {
			folder1.mkdir();
		}
		
		
		File[] target_file = folder1.listFiles(); // 복사할 파일들을 가져옴
		if(target_file.length == 0) {
			folder1.delete();
			return null;
		}
		if(!folder2.exists()) {
			folder2.mkdir();
		}
		for(File file : target_file) { // 각 파일 복사
			File temp = new File(folder2.getAbsolutePath() + File.separator + file.getName());
			
			if(file.isDirectory()) {
				temp.mkdir();
			}else {
				FileInputStream fis = null;
	            FileOutputStream fos = null;
	            try {
	                fis = new FileInputStream(file);
	                fos = new FileOutputStream(temp);
	                
	                byte[] b = new byte[4096];
	                int cnt = 0;
	                while ((cnt = fis.read(b)) != -1) {
	                    fos.write(b, 0, cnt);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                return null;
	            } finally {
	                try {
	                    fis.close();
	                    fos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	            file.delete(); // 파일 복사 후 임시 폴더에 있던 파일 삭제
			}
		}
		folder1.delete(); // 임시 폴더에 있던 사용자 폴더 삭제
		
		return targetPath.replace(FILE_UPLOAD_PATH, "");
	}
	
	// 저장된 파일 삭제
	public static void deleteImage(String imagePath) {
		if(imagePath == null) {
			return;
		}
		String fullFilePath = FILE_UPLOAD_PATH + imagePath.replace("/images", "");
		File targetFolder = new File(fullFilePath);
		File[] files = targetFolder.listFiles();
		if(files == null) {
			return;
		}
		for(File file : files) {
			file.delete();
		}
		targetFolder.delete();
	}
}
