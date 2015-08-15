/**
 * 
 */
package irsystem.prequeryprocess;

import irsystem.merge.PostingList;

import irsystem.tokenizer.TokenDetails;
import java.util.ArrayList;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class PreQuery {
	private Hashtable<Integer,Hashtable<String,Object>> termDetails;
	private Double[] normFact = new Double[200];
	private Double[] sumTFs = new Double[200];
	/**
	 * @return the sumTFs
	 */
	public Double[] getSumTFs() {
		return sumTFs;
	}

	/**
	 * @param sumTFs the sumTFs to set
	 */
	public void setSumTFs(Double[] sumTFs) {
		this.sumTFs = sumTFs;
	}

	private Double avgDocLength;
	
	public PreQuery(){
		setTermDetails(new Hashtable<Integer, Hashtable<String,Object>>());
	}
	
	/**
	 * @return the termWt
	 */
	public Hashtable<Integer, Hashtable<String, Object>> getTermDetails() {
		return termDetails;
	}

	/**
	 * @param termWt the termWt to set
	 */
	public void setTermDetails(Hashtable<Integer, Hashtable<String, Object>> termDetails) {
		this.termDetails = termDetails;
	} 
	
	public void processStep1(Hashtable<String,Object> tokenDict){
		
		Set<Entry<String, Object>> tokenSet = tokenDict.entrySet();
		Iterator<Entry<String, Object>> itr = tokenSet.iterator();
		Entry<String, Object> currentObj;
		
		while(itr.hasNext()){
			currentObj = itr.next();
			String token = currentObj.getKey();
			TokenDetails tokenDetailsObj = (TokenDetails) currentObj.getValue();
			
			int docFreq = tokenDetailsObj.getDocFreq();
			ArrayList<PostingList> postinglist = tokenDetailsObj.getPostingList();

			//Collections.sort(postinglist, new DocIDPostingComparator());
			
			Iterator<PostingList> itr1 = postinglist.iterator();
			PostingList currentObj1;
			while (itr1.hasNext()) {
				currentObj1 = itr1.next();
				int docID = currentObj1.getDocID();
				int termfq = currentObj1.getTermFreq();
				double termfqwt = this.calcTFWt(termfq);
				
				double terms[] = new double[2];
				terms[0] = Double.parseDouble(String.valueOf(termfq));
				terms[1] = termfqwt;
				
				//String postData = docID + " " + termfq;
				this.saveTotermWt(docID, token, terms);
				
				//Debug.printDebug(Debug.getDebugValue(),postData);
			}
			
		}
	}
	
	public void processStep2(){
		Set<Entry<Integer, Hashtable<String, Object>>> tokenSet = this.getTermDetails().entrySet();
		Iterator<Entry<Integer, Hashtable<String, Object>>> itr = tokenSet.iterator();
		Entry<Integer, Hashtable<String, Object>> currentObj;
		
		
		
		while(itr.hasNext()){
			currentObj = itr.next();
			Integer docid = currentObj.getKey();
			
			Set<Entry<String, Object>> tokenSet1 = currentObj.getValue().entrySet();
			Iterator<Entry<String, Object>> itr1 = tokenSet1.iterator();
			Entry<String, Object> currentObj1;
			double temp = 0.0;
			double temptf = 0.0;
			//System.out.println(" starting process2 for docid : "+docid);
			while(itr1.hasNext()){
				currentObj1 = itr1.next();
				double terms[] = (double[])currentObj1.getValue();
				double tempSq = terms[1]*terms[1];//currentObj1.getValue() * currentObj1.getValue();
				double tempmultf = terms[0]*terms[0];
				
				temp = temp + tempSq;
				temptf = temptf + tempmultf;
				//System.out.println("value of temp - " + temp + " for docid :"+docid);
				//this.getNormFact().add(e)
			}
			//System.out.println("value of temp - " + temp + " for docid :"+docid);
			temp = Math.sqrt(temp);
			//System.out.println("value of tem1p - " + temp + " for docid :"+docid);
			sumTFs[docid] = temptf;
			normFact[docid] = temp;
			temp = 0.0;
		}
		
		Double tempval = 0.0;
		for(int i =0;i<sumTFs.length;i++){
			tempval = tempval + sumTFs[i];
		}
		tempval = tempval / 200;
		setavgDocLength(tempval);
	}
	
	public double calcTFWt(Integer termFreq){
		return (1+ Math.log10(termFreq));
	}
	
	public void saveTotermWt(int docID,String token, double terms[]){
		
		if(!this.getTermDetails().containsKey(docID)){
			//add new 
			//Create hashtable for storing entries of tokens for this document
			Hashtable<String,Object> docEntry = new Hashtable<String, Object>();
			docEntry.put(token, terms);
			this.getTermDetails().put(docID, docEntry);
		} else {
			//update
			Hashtable<String,Object> oldValue = this.getTermDetails().get(docID); 			
			oldValue.put(token, terms);
		}
	}
	
	

	/**
	 * @return the normFact
	 */
	public Double[] getNormFact() {
		return normFact;
	}

	/**
	 * @param normFact the normFact to set
	 */
	public void setNormFact(Double[] normFact) {
		this.normFact = normFact;
	}

	/**
	 * @return the avgLength_TF
	 */
	public Double getavgDocLength() {
		return avgDocLength;
	}

	/**
	 * @param avgLength_TF the avgLength_TF to set
	 */
	public void setavgDocLength(Double avgDocLength) {
		this.avgDocLength = avgDocLength;
	}
	
}
