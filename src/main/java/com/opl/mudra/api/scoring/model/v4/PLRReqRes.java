package com.opl.mudra.api.scoring.model.v4;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PLRReqRes extends TrackingManageFieldsProxy implements Serializable {

	private static final long serialVersionUID = -93321449016462267L;

	private Double plr;
	
	private Integer type;

	private Date effectiveFromDate;

	private Date effectiveTillDate;

	private Integer scoringModelBasedOn;

	private List<RoiRange> roiRanges;

	private PLRReqRes oldPLR;

	private Long oldPLRId;

}
