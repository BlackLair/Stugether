package com.kuwon.stugether.editor;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/editor")
@RestController
public class EditorRestController {
	
	@GetMapping("/token")
	public String getEditorToken(){
		return UUID.randomUUID().toString();
	}
}
