/**
 * 
 */
package irsystem.processquery;


public class ResultsData {
	private String oti;
	private String ti;
	private Double wi;
	private Double dti;
	private Double qti;
	private Double wtq;
	private Double wtd;
	private Double doclen;
	
	public ResultsData(String otiIn,Double wiIn,Double dtiIn,Double qtiIn){
		setOti(otiIn);
		setWi(wiIn);
		setDti(dtiIn);
		setQti(qtiIn);
	}

	public ResultsData(String tiIn,Double wtqIn,Double wtdIn,Double doclenIn,int mode){
		setTi(tiIn);
		setWtq(wtqIn);
		setWtd(wtdIn);
		setDoclen(doclenIn);
	}

	/**
	 * @return the ti
	 */
	public String getTi() {
		return ti;
	}

	/**
	 * @param ti the ti to set
	 */
	public void setTi(String ti) {
		this.ti = ti;
	}

	/**
	 * @return the wi
	 */
	public Double getWi() {
		return wi;
	}

	/**
	 * @param wi the wi to set
	 */
	public void setWi(Double wi) {
		this.wi = wi;
	}

	/**
	 * @return the dti
	 */
	public Double getDti() {
		return dti;
	}

	/**
	 * @param dti the dti to set
	 */
	public void setDti(Double dti) {
		this.dti = dti;
	}

	/**
	 * @return the qti
	 */
	public Double getQti() {
		return qti;
	}

	/**
	 * @param qti the qti to set
	 */
	public void setQti(Double qti) {
		this.qti = qti;
	}

	/**
	 * @return the wtq
	 */
	public Double getWtq() {
		return wtq;
	}

	/**
	 * @param wtq the wtq to set
	 */
	public void setWtq(Double wtq) {
		this.wtq = wtq;
	}

	/**
	 * @return the wtd
	 */
	public Double getWtd() {
		return wtd;
	}

	/**
	 * @param wtd the wtd to set
	 */
	public void setWtd(Double wtd) {
		this.wtd = wtd;
	}

	/**
	 * @return the doclen
	 */
	public Double getDoclen() {
		return doclen;
	}

	/**
	 * @param doclen the doclen to set
	 */
	public void setDoclen(Double doclen) {
		this.doclen = doclen;
	}

	/**
	 * @return the oti
	 */
	public String getOti() {
		return oti;
	}

	/**
	 * @param oti the oti to set
	 */
	public void setOti(String oti) {
		this.oti = oti;
	}
	
}
