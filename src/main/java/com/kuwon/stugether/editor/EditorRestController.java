package com.kuwon.stugether.editor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kuwon.stugether.editor.service.EditorService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/editor")
@RestController
public class EditorRestController {
	@Autowired
	EditorService editorService;
	
	// 글 작성 에디터 고유 토큰 발급
	@GetMapping("/token")
	public String getEditorToken(){
		return UUID.randomUUID().toString();
	}
	
	// 에디터 삽입 이미지 임시 저장
	@PostMapping("/upload-image")
	public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file
										, @RequestParam("editorToken") String editorToken
										, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		String path = editorService.uploadTempImage(file, userId, editorToken);
		if(path != null) {
			resultMap.put("responseCode", "success");
			resultMap.put("url", path);
		}else {
			resultMap.put("responseCode", "error");
		}
		resultMap.put("editorToken", editorToken);
		return resultMap;
	}
	
	// 에디터 임시 이미지 제거
	@DeleteMapping("/delete-image")
	public void deleteImage(@RequestParam("file") String fileName
							, @RequestParam("editorToken") String editorToken
							, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		editorService.deleteTempImage(fileName, userId, editorToken);
	}
}
