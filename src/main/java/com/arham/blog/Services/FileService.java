package com.arham.blog.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String uploading(String path,MultipartFile file) throws IOException;
	
	InputStream getSource(String path,String fileName)throws FileNotFoundException;
	

}
