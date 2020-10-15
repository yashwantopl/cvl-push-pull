/**
 * 
 */
package com.opl.mudra.api.dms.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipRequest implements Serializable {
	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -3812592945044520004L;
		private Long applicationId;
	    private List<Long> productDocumentMappingIds;
	    private List<Long> coAppId;
	    private String userType;
	    private Long profileId;
	    
		/**
		 * @return the applicationId
		 */
		public Long getApplicationId() {
			return applicationId;
		}
		/**
		 * @param applicationId the applicationId to set
		 */
		public void setApplicationId(Long applicationId) {
			this.applicationId = applicationId;
		}
		/**
		 * @return the productDocumentMappingId
		 */
		
		/**
		 * @return the userType
		 */
		public String getUserType() {
			return userType;
		}
		/**
		 * @return the productDocumentMappingIds
		 */
		public List<Long> getProductDocumentMappingIds() {
			return productDocumentMappingIds;
		}
		/**
		 * @param productDocumentMappingIds the productDocumentMappingIds to set
		 */
		public void setProductDocumentMappingIds(List<Long> productDocumentMappingIds) {
			this.productDocumentMappingIds = productDocumentMappingIds;
		}
		/**
		 * @param userType the userType to set
		 */
		public void setUserType(String userType) {
			this.userType = userType;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		
		public List<Long> getCoAppId() {
			return coAppId;
		}
		/*@Override
		public String toString() {
			return "ZipRequest [applicationId=" + applicationId + ", productDocumentMappingIds="
					+ productDocumentMappingIds + ", coAppId=" + coAppId + ", userType=" + userType + "]";
		}*/
		public void setCoAppId(List<Long> coAppId) {
			this.coAppId = coAppId;
		}
		public Long getProfileId() {
			return profileId;
		}
		public void setProfileId(Long profileId) {
			this.profileId = profileId;
		}
}
