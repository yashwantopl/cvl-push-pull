package com.opl.mudra.api.dms.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dhaval on 25-Apr-17.
 */
public class DocumentResponse {

	private Integer id;
    private String message;
    private Integer status;
    private Object data;
    private List dataList;
    private Long storageId;
    private byte [] bytes;
    private Integer isUploadFrom;

    //constructor
    public DocumentResponse(String message, Integer status, List dataList) {
        this.message = message;
        this.status = status;
        this.dataList = dataList;
    }

    public DocumentResponse(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
    
    public DocumentResponse(String message, Integer status,Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public DocumentResponse() {
    }

    //getter setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public Integer getIsUploadFrom() {
		return isUploadFrom;
	}

	public void setIsUploadFrom(Integer isUploadFrom) {
		this.isUploadFrom = isUploadFrom;
	}
	
	@Override
	public String toString() {
		return "DocumentResponse [id=" + id + ", message=" + message + ", status=" + status + ", data=" + data
				+ ", dataList=" + dataList + ", storageId=" + storageId + ", bytes=" + Arrays.toString(bytes)
				+ ", isUploadFrom=" + isUploadFrom + "]";
	}
	
}
