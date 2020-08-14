package com.opl.mudra.api.scoring.model.v4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldMasterJsonValueRequest {

	private Long id;
	private String value;
	private String coSde;
	
	@Override
	public String toString() {
		return "FieldMasterJsonValueRequest [id=" + id  + "]";
	}
}
