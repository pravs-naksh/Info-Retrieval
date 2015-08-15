/**
 * 
 */
package irsystem.queryresults;

public class QueryResults {
	private Integer docID;
	private Double score;
	
	
	public QueryResults(Integer docIDin, Double scorein) {
		// TODO Auto-generated constructor stub
		setDocID(docIDin);
		setScore(scorein);
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
	public void setDocID(Integer docID) {
		this.docID = docID;
	}
	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
}
