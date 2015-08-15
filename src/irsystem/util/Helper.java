/**
 * 
 */
package irsystem.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import irsystem.merge.PostingList;
import irsystem.queryresults.QueryResults;
import irsystem.sort.DocIDPostingComparator;
import irsystem.tokenizer.TokenDetails;
import irsystem.tokenizer.TokenInfo;

public class Helper {
	
	private ArrayList<PostingList> postingList;
	private TokenInfo tokenInfoObj;
	private ArrayList<TokenInfo> tokenList;
	
	public Helper(){
		setPostingList(new ArrayList<PostingList>());
		setTokenInfoObj(new TokenInfo());
		setTokenList(new ArrayList<TokenInfo>());
	}
	
	public TokenInfo createStructure(String token,int docID){
		TokenInfo record = new TokenInfo();
		record.setDocID(docID);
		record.setToken(token);
		return record;
	}
	
	public void createTokenStructure(String token,int docFreq,ArrayList<PostingList> postinglist){
		TokenDetails record = new TokenDetails();
		record.setDocFreq(docFreq);
		record.setPostingList(postinglist);
		tokenInfoObj.getTokenDict().put(token, record);
		//record.setToken(token);
		//return record;
	}
	
	public TokenInfo createTokenStructure1(String token,int docFreq,ArrayList<PostingList> postinglist){
		TokenInfo record = new TokenInfo();
		record.setToken(token);
		record.setDocFreq(docFreq);
		record.setPostingList(postinglist);
		//tokenInfoObj.getTokenDict().put(token, record);
		return record;
	}
	
	public PostingList createPostingStructure(int termFreq,int docID){
		PostingList record = new PostingList();
		record.setDocID(docID);
		record.setTermFreq(termFreq);
		return record;
	}
	
	public QueryResults createQueryResultsStructure(Integer docID,Double score){
		QueryResults record = new QueryResults(docID,score);
		return record;
	}
	
	@SuppressWarnings("unchecked")
	public void merge(ArrayList<TokenInfo> listoftokens){
		Iterator<TokenInfo> itr = listoftokens.iterator();
		TokenInfo currentObj;
		String token = null;
		String temp = null;
		int docID = 0;
		int docIDtemp = 0;
		Integer docCount = 1;
		Integer termCount = 0;
		int start = 0;
		while (itr.hasNext()) {
			currentObj = itr.next();
			token = currentObj.getToken();
			docID = currentObj.getDocID();
			if(start == 0){
				temp = token;
				docIDtemp = docID;
				start = 1;
			}
			
			if(token.equals(temp) && !(temp.isEmpty()||token.isEmpty())){
				if(docID != docIDtemp){
					//save tf in posting-list with current docid and make termCount = 0
					postingList.add(this.createPostingStructure(termCount, docIDtemp));
//					if(token.equals("a") || token.equals("in")){
//						System.out.println("DocID: " + docIDtemp + " TermFq " +termCount);
//					}
					termCount = 1;
					docCount++;
				} else {
					termCount++;
				}
			}
			
			
			if(!token.equals(temp) && !(temp.isEmpty()||token.isEmpty())){
				//save docCount as doc freq this temp and start new count now for token
				postingList.add(this.createPostingStructure(termCount, docIDtemp));
				//here it will add the new tokens to hashtable ... 
				tokenList.add(this.createTokenStructure1(temp, docCount, (ArrayList<PostingList>) postingList.clone()));
				this.createTokenStructure(temp, docCount, (ArrayList<PostingList>) postingList.clone());
				postingList.clear();
				docCount = 1;
			}
			
			temp = token;
			docIDtemp = docID;
			
			if(!itr.hasNext()){
				postingList.add(this.createPostingStructure(termCount, docIDtemp));
				tokenList.add(this.createTokenStructure1(temp, docCount, (ArrayList<PostingList>) postingList.clone()));
				this.createTokenStructure(temp, docCount, (ArrayList<PostingList>) postingList.clone());
				postingList.clear();
			}
//			if(token.equals("in")) 
//				System.out.println("Token: " + token + " DocID: " + docID + " docFreq: " + docCount +
//						" tempdocid:" + docIDtemp);
		}
		
		//return tokenInfoObj;
	}
	
	public void generateFiles(String dictName, String postName, ArrayList<TokenInfo> tokenList){
		DataWriter dictWriter = new DataWriter(dictName);
		DataWriter postWriter = new DataWriter(postName);

		int count_offset = Debug.getCount_offset_VALUE();
		//Hashtable<String,Object> tokenDict = arrayList.getTokenDict();
		
		//Set<Entry<String, Object>> tokenSet = tokenDict.entrySet();
		
		//Iterator<Entry<String, Object>> itr = tokenSet.iterator();
		//Entry<String, Object> currentObj;

		Iterator<TokenInfo> itr = tokenList.iterator();
		TokenInfo currentObj;
		
		while (itr.hasNext()) {
			currentObj = itr.next();
			String token = currentObj.getToken();
			//TokenDetails detailsObj = (TokenDetails) currentObj.getValue();
			
			int docFreq = currentObj.getDocFreq();
			ArrayList<PostingList> postinglist = currentObj.getPostingList();

			Collections.sort(postinglist, new DocIDPostingComparator());
			
			String dictData = token + " " + docFreq
					+ " " + count_offset;
			Debug.printDebug(2,dictData);
			dictWriter.writeLn(dictData);
			
			Debug.printDebug(2," Posting-List => ");
			
			Iterator<PostingList> itr1 = postinglist.iterator();
			PostingList currentObj1;
			while (itr1.hasNext()) {
				currentObj1 = itr1.next(); 
				int docID = currentObj1.getDocID();
				int termfq = currentObj1.getTermFreq();
				String postData = docID + " "+termfq+" " + (1+Math.log10(termfq));

				Debug.printDebugLn(2,postData);
				postWriter.writeLn(postData);
				count_offset++;
			}
			Debug.printDebugLn(2, "");
		}
		dictWriter.close();
		postWriter.close();
		//Debug.printDebugLn(Debug.getDebugValue(),"length of tokenlist is : " + tokenDict.size());
	}

	/**
	 * @return the postingList
	 */
	public ArrayList<PostingList> getPostingList() {
		return postingList;
	}

	/**
	 * @param tokenList the postingList to set
	 */
	public void setPostingList(ArrayList<PostingList> tokenList) {
		this.postingList = tokenList;
	}

	/**
	 * @return the tokenInfoObj
	 */
	public TokenInfo getTokenInfoObj() {
		return tokenInfoObj;
	}

	/**
	 * @param tokenInfoObj the tokenInfoObj to set
	 */
	public void setTokenInfoObj(TokenInfo tokenInfoObj) {
		this.tokenInfoObj = tokenInfoObj;
	}

	/**
	 * @return the tokenList
	 */
	public ArrayList<TokenInfo> getTokenList() {
		return tokenList;
	}

	/**
	 * @param tokenList the tokenList to set
	 */
	public void setTokenList(ArrayList<TokenInfo> tokenList) {
		this.tokenList = tokenList;
	}
}
