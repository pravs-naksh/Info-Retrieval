/**
 * 
 */
package irsystem.processquery;

import irsystem.merge.PostingList;
import irsystem.prequeryprocess.PreQuery;
import irsystem.queryresults.QueryResults;
import irsystem.sort.ScoreComparator;
import irsystem.tokenizer.TokenDetails;
import irsystem.util.DataWriter;
import irsystem.util.Debug;
import irsystem.util.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class ProcessQuery {
	private Hashtable<String, Integer> tokenTermFq = new Hashtable<String, Integer>();
	private Hashtable<String, Object> docDict;
	private Hashtable<String, Object> queryDict = new Hashtable<String, Object>();
	private Hashtable<Integer, String> docIDTokens = new Hashtable<Integer, String>();
	private ArrayList<QueryResults> finalScores = new ArrayList<QueryResults>();
	private ArrayList<QueryResults> finalScoresOkapi = new ArrayList<QueryResults>();
	private Hashtable<Integer, Double> finalScoresHT = new Hashtable<Integer, Double>();
	private Hashtable<Integer, Double> finalScoresOkapiHT = new Hashtable<Integer, Double>();
	
	
	private Hashtable<Integer, ArrayList<ResultsData>> okaresults = new Hashtable<Integer, ArrayList<ResultsData>>();
	private Hashtable<Integer, ArrayList<ResultsData>> cosresults = new Hashtable<Integer, ArrayList<ResultsData>>();
	
	private PreQuery preQueryobj;
	Helper helper = new Helper();
	
	
	
	public ProcessQuery(Hashtable<String, Object> docDictIn, PreQuery preQueryobjIn) {
		// TODO Auto-generated constructor stub
		setDocDict(docDictIn);
		setPreQueryobj(preQueryobjIn);
	}
	/**
	 * @return the docDict
	 */
	public Hashtable<String, Object> getDocDict() {
		return docDict;
	}

	/**
	 * @param docDict the docDict to set
	 */
	public void setDocDict(Hashtable<String, Object> docDict) {
		this.docDict = docDict;
	}

	/**
	 * @return the tokenTermFq
	 */
	public Hashtable<String, Integer> getTokenTermFq() {
		return tokenTermFq;
	}

	/**
	 * @param tokenTermFq the tokenTermFq to set
	 */
	public void setTokenTermFq(Hashtable<String, Integer> tokenTermFq) {
		this.tokenTermFq = tokenTermFq;
	}

	/**
	 * @return the preQueryobj
	 */
	public PreQuery getPreQueryobj() {
		return preQueryobj;
	}

	/**
	 * @param preQueryobj the preQueryobj to set
	 */
	public void setPreQueryobj(PreQuery preQueryobj) {
		this.preQueryobj = preQueryobj;
	}

	/**
	 * @return the queryDict
	 */
	public Hashtable<String, Object> getQueryDict() {
		return queryDict;
	}
	/**
	 * @param queryDict the queryDict to set
	 */
	public void setQueryDict(Hashtable<String, Object> queryDict) {
		this.queryDict = queryDict;
	}
	/**
	 * @return the docIDTokens
	 */
	public Hashtable<Integer, String> getDocIDTokens() {
		return docIDTokens;
	}
	/**
	 * @param docIDTokens the docIDTokens to set
	 */
	public void setDocIDTokens(Hashtable<Integer, String> docIDTokens) {
		this.docIDTokens = docIDTokens;
	}
	
	/**
	 * @return the finalScoresHT
	 */
	public Hashtable<Integer, Double> getFinalScoresHT() {
		return finalScoresHT;
	}
	/**
	 * @param finalScoresHT the finalScoresHT to set
	 */
	public void setFinalScoresHT(Hashtable<Integer, Double> finalScoresHT) {
		this.finalScoresHT = finalScoresHT;
	}

	
	/**
	 * @return the finalScoresOkapiHT
	 */
	public Hashtable<Integer, Double> getFinalScoresOkapiHT() {
		return finalScoresOkapiHT;
	}
	/**
	 * @param finalScoresOkapiHT the finalScoresOkapiHT to set
	 */
	public void setFinalScoresOkapiHT(Hashtable<Integer, Double> finalScoresOkapiHT) {
		this.finalScoresOkapiHT = finalScoresOkapiHT;
	}
	
	
	public void step1() {
		// TODO Auto-generated method stub
		
		Set<Entry<String, Integer>> tokenTermFqset = tokenTermFq
				.entrySet();
		Iterator<Entry<String, Integer>> tokenTermFqitr = tokenTermFqset
				.iterator();
		Entry<String, Integer> currentEntry;

		 
		
		while (tokenTermFqitr.hasNext()) {
			currentEntry = tokenTermFqitr.next();
			String queryToken = currentEntry.getKey();
			// lets fire a search on our HT1 ->
			//System.out.println("Step 1: lets fire a search with stemmed: " + tokenforq+" on our HT1 -> qterm : " + queryToken +" tfq :"+currentEntry.getValue());
			// tokenObj.getTokenDict()
			//System.out.println(tokenObj.getTokenDict().containsKey(tokenforq));
			if (docDict.containsKey(queryToken)) {
				//++hit_count;
				//current_precision = hit_count / count_doc;
				TokenDetails tokenDetObj = (TokenDetails) docDict.get(queryToken);
				queryDict.put(queryToken, tokenDetObj);

				ArrayList<PostingList> postinglist = tokenDetObj
						.getPostingList();
				Iterator<PostingList> postinglistitr = postinglist
						.iterator();
				PostingList currentpostinglist;
				/* if(hit_count == 5 || hit_count == 10){
					System.out.println("Precision@"+hit_count+": "+current_precision);
				} */
				while (postinglistitr.hasNext()) {
					currentpostinglist = postinglistitr.next();
					Integer postdocID = currentpostinglist
							.getDocID();
					if (!docIDTokens.containsKey(postdocID)) {
						//System.out.println("new docid found puting in ht");
						docIDTokens.put(postdocID, queryToken);
					} else {
						
						String prevTokenstring = docIDTokens
								.get(postdocID);
						docIDTokens.put(postdocID, prevTokenstring
								+ "," + queryToken);
						//System.out.println("docid already there .... so appended value - " + prevTokenstring
							//	+ "," + queryToken);
					}
				}
			} else {

			}
			//++count_doc;
		} // collection of tokenTermFq => token+tf | queryDict => token + object->df,posting list | docIDTokens=> docid + csv of query tokens
		
	}
	
	public void step2(){
		Set<Entry<Integer,String>> docIDTokenset = docIDTokens
				.entrySet();
		Iterator<Entry<Integer,String>> docIDTokenitr = docIDTokenset
				.iterator();
		Entry<Integer,String> currendocIDTokentEntry;
		
		
		while(docIDTokenitr.hasNext()){
			currendocIDTokentEntry = docIDTokenitr.next();
			Integer currdocID = currendocIDTokentEntry.getKey();
			//iterate over queryDict 
			Set<Entry<String, Object>> queryDictset = queryDict
					.entrySet();
			Iterator<Entry<String, Object>> queryDictitr = queryDictset
					.iterator();
			Entry<String, Object> currentqueryDictlist;
			Double tempProd = 0.0;
			Double tempProdOkapi = 0.0;
			//System.out.println("length of queryDictitr " + queryDict.size());
			
			ArrayList<ResultsData> okaListterms = new ArrayList<ResultsData>();
			ArrayList<ResultsData> cosListterms = new ArrayList<ResultsData>();
			
			while(queryDictitr.hasNext()){
				currentqueryDictlist = queryDictitr.next();
				String qtoken = currentqueryDictlist.getKey();
				
				//first use docid 
				Hashtable<String,Object> termwt_ht = preQueryobj.getTermDetails().get(currdocID);
				Double prod = 0.0;
				Double prodOkapi = 0.0;
				
				if(termwt_ht.containsKey(qtoken)){
					//System.out.println("found:"+qtoken);
					//++hit_count;
					//current_precision = hit_count / count_doc;
					double currTerm[] = (double[])termwt_ht.get(qtoken);
					
					double currTermWt = currTerm[1];
					double currTermFq = currTerm[0];
					
					Double normalizedwtTerm = currTermWt / preQueryobj.getNormFact()[currdocID];
					Double docLength = preQueryobj.getSumTFs()[currdocID];
					
//					if(qtoken.equals("nike") || qtoken.equals("climb")){
//						System.out.println(currdocID+" normalizedwtTerm for "+currTermWt+"/"+preQueryobj.getNormFact()[currdocID]+"="+qtoken+": "+normalizedwtTerm);
//					}
					
					TokenDetails tokenObj = (TokenDetails)currentqueryDictlist.getValue();
					//Calculate tf-wt of query 
					Double tfwt = 0.0;
					int qtf = 0;
					if(tokenTermFq.containsKey(qtoken)){
						qtf = tokenTermFq.get(qtoken);
						tfwt = 1+Math.log10(qtf);
					} 
					Integer df = tokenObj.getDocFreq();
					
					Double idf = Math.log10(Debug.getNumberofDocs_VALUE()/df);
					
					Double querywt = tfwt * idf;
					
					/* if(qtoken.equals("nike") || qtoken.equals("climb")){
						System.out.println(currdocID + "querywt for "+qtoken+": "+querywt);
					} */
					prod = querywt*normalizedwtTerm;
					
					Double wi = Math.log((Debug.getNumberofDocs_VALUE() - df + 0.5)/(df+0.5));
					Double dti_part1 = ( (1-Debug.getOkapi_b_VALUE()) + Debug.getOkapi_b_VALUE() * docLength/preQueryobj.getavgDocLength());
					
					Double dti = ((Debug.getOkapi_k1_VALUE()+1)*currTermFq) /(Debug.getOkapi_k1_VALUE()* dti_part1 + currTermFq);
					
					Double qti = (Debug.getOkapi_k3_VALUE() + 1) * qtf /(Debug.getOkapi_k3_VALUE() + qtf);
					
					prodOkapi = wi * dti * qti;
					
					ResultsData oka = new ResultsData(qtoken,wi,dti,qti);
					ResultsData cos = new ResultsData(qtoken,querywt,tfwt,docLength);
					okaListterms.add(oka);
					cosListterms.add(cos);
					/* if(hit_count == 5 || hit_count == 10){
						System.out.println("Precision@"+hit_count+": "+current_precision);
					} */
				} //else System.out.println("not f"+qtoken);
				
				
				tempProd = tempProd + prod;
				tempProdOkapi = tempProdOkapi + prodOkapi;
			}
			//now we have calculated score for this docid with all term wts of QUERY AND DOCUMENT in tempProd
			okaresults.put(currdocID, okaListterms);
			cosresults.put(currdocID, cosListterms);
			finalScores.add(helper.createQueryResultsStructure(currdocID, tempProd));
			finalScoresHT.put(currdocID, tempProd);
			finalScoresOkapi.add(helper.createQueryResultsStructure(currdocID, tempProdOkapi));
			finalScoresOkapiHT.put(currdocID, tempProdOkapi);
			//++count_doc;
		}
		//System.out.println("no of docs: "+ count_doc);
	}
	
	public void cleartables(){
		// TODO Auto-generated method stub
		if(!tokenTermFq.isEmpty())
			tokenTermFq.clear();
		if(!finalScores.isEmpty())
			finalScores.clear();
		if(!queryDict.isEmpty())
			queryDict.clear();
		if(!docIDTokens.isEmpty())
			docIDTokens.clear();
	}
	
	public void step3(int index,String fullQuery) {
		// TODO Auto-generated method stub
		DataWriter queryresWriter = new DataWriter("cosquery"+index+"result.txt");
		
		Collections.sort(finalScores,new ScoreComparator());
		
		Iterator<QueryResults> queryresitr = finalScores.iterator();
		QueryResults currentqueryreslist;
		
		int counter = 0;
		Debug.printDebugLn(0,"For query term : " + fullQuery);
		Debug.printDebugLn(1,"For query term : " + fullQuery);
		while(queryresitr.hasNext()){
			++counter;
			currentqueryreslist = queryresitr.next();
			Integer docid = currentqueryreslist.getDocID();
			//iterate here
			ArrayList<ResultsData> obj = cosresults.get(docid);
			Iterator<ResultsData> cositr = obj.iterator();
			ResultsData currentcoslist;
			while(cositr.hasNext()){
				currentcoslist = cositr.next();
				String termi = currentcoslist.getOti() +" "
						+" "+ currentcoslist.getWi() 
						+" "+ currentcoslist.getDti() 
						+" "+ currentcoslist.getQti();
				queryresWriter.writeLn(termi);
			}
			String queryResult = docid +" "+currentqueryreslist.getScore();
			Debug.printDebugLn(0,queryResult);
			Debug.printDebugLn(1,queryResult);
			queryresWriter.writeLn(queryResult);
			if(counter>9) break;
		}
		Debug.printDebugLn(0, "");
		Debug.printDebugLn(1, "");
		queryresWriter.close();
		
	}
	
	public void step4(int index,String fullQuery) {
		// TODO Auto-generated method stub
		DataWriter queryresWriter = new DataWriter("okaquery"+index+"result.txt");
		System.out.println("=------=");
		Collections.sort(finalScoresOkapi,new ScoreComparator());
		
		Iterator<QueryResults> queryresitr = finalScoresOkapi.iterator();
		QueryResults currentqueryreslist;
		
		int counter = 0;
		Debug.printDebugLn(0,"For query term : " + fullQuery);
		Debug.printDebugLn(1,"For query term : " + fullQuery);
		while(queryresitr.hasNext()){
			++counter;
			currentqueryreslist = queryresitr.next();
			Integer docid = currentqueryreslist.getDocID();
			//iterate here
			ArrayList<ResultsData> obj = okaresults.get(docid);
			Iterator<ResultsData> okaitr = obj.iterator();
			ResultsData currentokalist;
			while(okaitr.hasNext()){
				currentokalist = okaitr.next();
				String termi = currentokalist.getTi() +" "
						+" "+ currentokalist.getWtq()
						+" "+ currentokalist.getWtd() 
						+" "+ currentokalist.getDoclen();
				queryresWriter.writeLn(termi);
			}
			String queryResult = currentqueryreslist.getDocID() +" "+currentqueryreslist.getScore();
			Debug.printDebugLn(0,queryResult);
			Debug.printDebugLn(1,queryResult);
			queryresWriter.writeLn(queryResult);
			if(counter>9) break;
		}
		Debug.printDebugLn(0, "");
		Debug.printDebugLn(1, "");
		queryresWriter.close();
		
	}
	
	public void step5(String fullQuery){
		
		Double hit_count = 0.0;
		Double current_precision = 0.0;
		Double hit_countOkapi = 0.0;
		Double current_precisionOkapi = 0.0;
		
		int doc_count = Debug.getNumberofDocs_VALUE();
		Double temp = 0.0;
		Double tempOkapi = 0.0;
		Double avgPrecisionat5 = 0.0;
		Double avgPrecisionOkapiat5 = 0.0;
		Double avgPrecisionat10 = 0.0;
		Double avgPrecisionOkapiat10 = 0.0;
		
		
		for(int i=1;i<=doc_count;i++){
			
			if(finalScoresHT.containsKey(i)){
				++hit_count;
				current_precision = hit_count / i;
				temp = temp + current_precision;
				//System.out.println("docid:"+ i +"size of HT res: "+finalScoresHT.size());
				//System.out.println("Query:"+ fullQuery +" -- Precision@"+i+" hits: "+hit_count+": "+current_precision);
			}
			if(finalScoresOkapiHT.containsKey(i)){
				++hit_countOkapi;
				current_precisionOkapi = hit_countOkapi / i;
				tempOkapi = tempOkapi + current_precisionOkapi;
				//System.out.println("docid:"+ i +"size of HT res: "+finalScoresHT.size());
				//System.out.println("Query:"+ fullQuery +" -- Precision@"+i+" hits: "+hit_count+": "+current_precision);
			}
			
			if(i == 5){
				avgPrecisionat5 = temp / 5;
				avgPrecisionOkapiat5 = tempOkapi / 5;
				//System.out.println("Query:"+ fullQuery +"Precision@"+i+" hits: "+hit_count+": "+current_precision);
			} else if(i == 10){
				avgPrecisionat10 = temp / 5;
				avgPrecisionOkapiat10 = tempOkapi / 5;
			}
			
			if(i>10) break;
		}
		System.out.println("MAP@5: "+ avgPrecisionat5);
		System.out.println("MAP@okapi@5: "+ avgPrecisionOkapiat5);
		System.out.println("MAP@10: "+ avgPrecisionat10);
		System.out.println("MAP@okapi@10: "+ avgPrecisionOkapiat10);
		System.out.println("");
	}
	/**
	 * @return the okaresults
	 */
	public Hashtable<Integer, ArrayList<ResultsData>> getOkaresults() {
		return okaresults;
	}
	/**
	 * @param okaresults the okaresults to set
	 */
	public void setOkaresults(Hashtable<Integer, ArrayList<ResultsData>> okaresults) {
		this.okaresults = okaresults;
	}
	/**
	 * @return the cosresults
	 */
	public Hashtable<Integer, ArrayList<ResultsData>> getCosresults() {
		return cosresults;
	}
	/**
	 * @param cosresults the cosresults to set
	 */
	public void setCosresults(Hashtable<Integer, ArrayList<ResultsData>> cosresults) {
		this.cosresults = cosresults;
	}
	
	
}
