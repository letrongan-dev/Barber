package com.myproject.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.myproject.entity.MyFile;

public interface FileService {
	File getFolderUpload();
	int uploadFile(MyFile myFile,HttpServletRequest request);
}
