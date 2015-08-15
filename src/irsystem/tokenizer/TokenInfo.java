/**
 * 
 */
package irsystem.tokenizer;

import irsystem.merge.PostingList;

import java.util.ArrayList;
import java.util.Hashtable;

public class TokenInfo {
	private Integer docID;
	private String token;
	private Integer docFreq;
	private ArrayList<PostingList> postingList;
	
	private Hashtable<String,Object> tokenDict;
	
	public TokenInfo(){
		setTokenDict(new Hashtable<String, Object>());
	}
	
	/**
	 * @return the docID
	 */
	public Integer getDocID() {
		return docID;
	}
	/**
	 * @param docID the docID to set
	 */
	public void setDocID(int docID) {
		this.docID = docID;
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
	/**
	 * @return the tokenDict
	 */
	public Hashtable<String,Object> getTokenDict() {
		return tokenDict;
	}
	/**
	 * @param tokenDict the tokenDict to set
	 */
	public void setTokenDict(Hashtable<String,Object> tokenDict) {
		this.tokenDict = tokenDict;
	}
	
}
