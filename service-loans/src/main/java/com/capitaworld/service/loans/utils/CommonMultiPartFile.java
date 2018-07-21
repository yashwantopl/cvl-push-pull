package com.capitaworld.service.loans.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class CommonMultiPartFile implements MultipartFile {

	private final byte[] imgContent;
	private final String originalFileName;
    private final String contentType;
	
	public CommonMultiPartFile(byte[] imgContent,String originalFileName,String contentType) {
		this.imgContent = imgContent;
		this.originalFileName=originalFileName;
		this.contentType=contentType;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return  null;//originalFileName;
	}

	@Override
	public String getOriginalFilename() {
		// TODO Auto-generated method stub
		
		return originalFileName;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return imgContent == null || imgContent.length == 0;
	}

	@Override
	public long getSize() {
		// TODO Auto-generated method stub
		return imgContent.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		// TODO Auto-generated method stub
		return imgContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {

		return new ByteArrayInputStream(imgContent);
	}

	@SuppressWarnings("resource")
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		// TODO Auto-generated method stub
		new FileOutputStream(dest).write(imgContent);

	}

}
