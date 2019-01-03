package com.capitaworld.service.loans.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sanket
 *
 */
public class DDRMultipart implements MultipartFile {

	private static final Logger logger = LoggerFactory.getLogger(DDRMultipart.class);
	
	private final byte[] imgContent;
	
	public DDRMultipart(byte[] imgContent)
    {
       this.imgContent = imgContent;
        }

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getOriginalFilename() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
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

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(dest);
			fileOutputStream.write(imgContent);
		}
		catch (Exception  e) {
			logger.error("Exception in DDRMultipart : ",e);
		}
		finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

}
