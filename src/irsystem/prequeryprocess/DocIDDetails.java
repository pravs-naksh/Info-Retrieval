/**
 * 
 */
package irsystem.prequeryprocess;

public class DocIDDetails {
	private String token;
	private Double termFreqWt;
	
	/***
	 * 
	 * @param tokenIn
	 * @param termFreqWtIn
	 */
	public DocIDDetails(String tokenIn,Double termFreqWtIn){
		setToken(tokenIn);
		setTermFreqWt(termFreqWtIn);
	}
	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the termFreqWt
	 */
	public Double getTermFreqWt() {
		return termFreqWt;
	}
	/**
	 * @param termFreqWt the termFreqWt to set
	 */
	public void setTermFreqWt(Double termFreqWt) {
		this.termFreqWt = termFreqWt;
	}
	
}
