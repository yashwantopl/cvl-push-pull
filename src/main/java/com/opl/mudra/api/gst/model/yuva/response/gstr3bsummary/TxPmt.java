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
public class TxPmt {
	
	@JsonProperty("tx_py")
	private TxPy[] txPy;
	
	private PdCash[] pdcash;

	private Pditc pditc;


	/**
	 * @return the pditc
	 */
	public Pditc getPditc() {
		return pditc;
	}

	/**
	 * @param pditc the pditc to set
	 */
	public void setPditc(Pditc pditc) {
		this.pditc = pditc;
	}

	/**
	 * @return the pdcash
	 */
	public PdCash[] getPdcash() {
		return pdcash;
	}

	/**
	 * @param pdcash the pdcash to set
	 */
	public void setPdcash(PdCash[] pdcash) {
		this.pdcash = pdcash;
	}

	/**
	 * @return the txPy
	 */
	public TxPy[] getTxPy() {
		return txPy;
	}

	/**
	 * @param txPy the txPy to set
	 */
	public void setTxPy(TxPy[] txPy) {
		this.txPy = txPy;
	}


	public TxPmt() {
		this.txPy = new TxPy[0];
		this.pdcash = new PdCash[0];
		this.pditc = new Pditc();
	}
}
