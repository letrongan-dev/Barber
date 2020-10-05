package com.myproject.impl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.entity.MyFile;

@Service
public class FileServiceImpl implements com.myproject.service.FileService {

	@Override
	public File getFolderUpload() {
		File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
	    if (!folderUpload.exists()) {
	      folderUpload.mkdirs();
	    }
	    return folderUpload;

	}

	@Override
	public int uploadFile(MyFile myFile,HttpServletRequest request) {
		
		try {
		      MultipartFile multipartFile = myFile.getMultipartFile();
		      String fileName = multipartFile.getOriginalFilename();
		      File file = new File(request.getServletContext().getRealPath("/"), fileName);
		      //String pathFile = request.getServletContext().getRealPath("/"+fileName);
		      System.err.println("hello "+request.getServletContext().getRealPath("/"+fileName));
		      multipartFile.transferTo(file);
		    } catch (Exception e) {
		      e.printStackTrace();
		      return 1;
		    }
		    return 0;
	}

}
