package com.example.sghousie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {
	
	private static final String UPLOAD_FOLDER = "";

	@RequestMapping("/admin")
	public String admin() {
		return "admin.html";
	}
	
	@RequestMapping("/easy")
	public String easy() {
		return "adminauto.html";
	}
	
	@RequestMapping("/mix")
	public String mix() {
		return "adminmix.html";
	}
	
	@RequestMapping("/mast")
	public String speechboard() {
		return "speechboard.html";
	}
	
	
	//@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		System.out.println("aaaaaaaaaaa");
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER+file.getOriginalFilename());
			Files.write(path, bytes);
			System.out.println(path);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

	//@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "message";
	}
}
