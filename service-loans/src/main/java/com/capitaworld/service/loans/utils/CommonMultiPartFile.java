package com.capitaworld.service.loans.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class CommonMultiPartFile implements MultipartFile {

	private static final Logger logger = LoggerFactory.getLogger(CommonMultiPartFile.class);

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
		return  null;//originalFileName;
	}

	@Override
	public String getOriginalFilename() {
		return originalFileName;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return imgContent == null || imgContent.length == 0;
	}

	@Override
	public long getSize() {
		return imgContent.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return imgContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {

		return new ByteArrayInputStream(imgContent);
	}

	@SuppressWarnings("resource")
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(dest);
			fileOutputStream.write(imgContent);
		}
		catch (Exception  e) {
			logger.error("Exception in CommonMultipart : ",e);
		}
		finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

}
