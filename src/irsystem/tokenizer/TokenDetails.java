/**
 * 
 */
package irsystem.tokenizer;

import irsystem.merge.PostingList;

import java.util.ArrayList;

public class TokenDetails {
	private Integer docID;
	private Integer docFreq;
	private ArrayList<PostingList> postingList;
	
	
	/**
	 * @return the docID
	 */
	public Integer getDocID() {
		return docID;
	}
	/**
	 * @param docID the docID to set
	 */
	public void setDocID(Integer docID) {
		this.docID = docID;
	}
	/**
	 * @return the docFreq
	 */
	public Integer getDocFreq() {
		return docFreq;
	}
	/**
	 * @param docFreq the docFreq to set
	 */
	public void setDocFreq(Integer docFreq) {
		this.docFreq = docFreq;
	}
	/**
	 * @return the postingList
	 */
	public ArrayList<PostingList> getPostingList() {
		return postingList;
	}
	/**
	 * @param postingList the postingList to set
	 */
	public void setPostingList(ArrayList<PostingList> postingList) {
		this.postingList = postingList;
	}
	
}
