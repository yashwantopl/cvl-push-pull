 /**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary;

 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

 /**
  * @author sanket
  *
  */


 @JsonIgnoreProperties(ignoreUnknown=true)
 @JsonInclude(Include.NON_NULL)
 public class IntrLtFee {

     @JsonProperty("ltfee_details")
     private LtFeeDetails ltFeeDetails;

     /**
      * @return the ltFeeDetails
      */
     public LtFeeDetails getLtFeeDetails() {
         return ltFeeDetails;
     }

     /**
      * @param ltFeeDetails the ltFeeDetails to set
      */
     public void setLtFeeDetails(LtFeeDetails ltFeeDetails) {
         this.ltFeeDetails = ltFeeDetails;
     }


     public IntrLtFee() {
         this.ltFeeDetails = new LtFeeDetails();
     }
 }
