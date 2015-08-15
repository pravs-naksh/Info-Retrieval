/**
 * 
 */
package irsystem.merge;

public class PostingList {
	private Integer DocID;
	private Integer termFreq;
	/**
	 * @return the docID
	 */
	public Integer getDocID() {
		return DocID;
	}
	/**
	 * @param docID the docID to set
	 */
	public void setDocID(Integer docID) {
		DocID = docID;
	}
	/**
	 * @return the termFreq
	 */
	public Integer getTermFreq() {
		return termFreq;
	}
	/**
	 * @param termFreq the termFreq to set
	 */
	public void setTermFreq(Integer termFreq) {
		this.termFreq = termFreq;
	}
}
