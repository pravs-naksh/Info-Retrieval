/**
 * 
 */
package irsystem.tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import irsystem.processquery.ProcessQuery;
import irsystem.queryresults.QueryResults;
import irsystem.sort.DocIDComparator;
import irsystem.sort.TokenComparator;
import irsystem.util.DataReader;
import irsystem.util.Debug;
import irsystem.util.Helper;

public class Tokenizer {

	private DataReader reader;
	private ArrayList<TokenInfo> tokenList = new ArrayList<TokenInfo>();
	
	private ArrayList<QueryResults> finalScores;
	private ProcessQuery processQuery;
	
	Helper helper = new Helper();

	public Tokenizer(String fileNameIn) {
		setReader(new DataReader(fileNameIn));
	}

	public Tokenizer(String fileNameIn, ProcessQuery processQueryIn) {
		// TODO Auto-generated constructor stub
		setReader(new DataReader(fileNameIn));
		setProcessQuery(processQueryIn);
	}

	public DataReader getReader() {
		return reader;
	}

	public void setReader(DataReader reader) {
		this.reader = reader;
	}

	public void createToken(int mode) {
		// this will create a list of tokens and save in ARRAYLIST
		BufferedReader br = reader.getBR();
		String sCurrentLine;
		int docID = Debug.getDocID_VALUE();
		TextUtils textUtils = new TextUtils();

		try {
			int index = 1;
			while ((sCurrentLine = br.readLine()) != null) {
				
				String details[] = sCurrentLine.split(" ");
				for (int i = 0; i < details.length; i++) {
					char[] chars = details[i].toCharArray();
					String token = "";
					/***
					 * Remove special special symbols and punctuations
					 * (apostrophes, hyphens, periods in numbers, space between
					 * numbe rs, parentheses, etc.) and case-folding
					 */
					for (int j = 0; j < chars.length; j++) {
						if (Character.isLetterOrDigit(chars[j])) {
							String normalizedChar = textUtils
									.normalize(Character.toString(chars[j]));
							token += normalizedChar;
						}
					}

					/***
					 * Stemming - Using PorterÕs Stemmer Algorithm
					 */
					Stemmer stemmer = new Stemmer();
					for (char s : token.toCharArray()) {
						stemmer.add(s);
					}
					stemmer.stem();
					token = stemmer.toString();

					/***
					 * Add this token to the arraylist
					 */
					if (mode == 1) {
						if (!token.isEmpty() && token != null) {
							tokenList.add(helper.createStructure(token, docID));
						}
					} else if (mode == 2) {
						// Step 1: add to queryList ()
						if (!token.isEmpty() && token != null) {
							// add tp Hashtable tokenTermFq and just increment
							// the TF
							if (processQuery.getTokenTermFq().containsKey(token)) {
								Integer val = processQuery.getTokenTermFq().get(token);
								val = val + 1;
								processQuery.getTokenTermFq().put(token, val);
							} else {
								processQuery.getTokenTermFq().put(token, 1);
							}
						}
					}
					
				}
				// query process starts
				if (mode == 2) {
					//lets start with saving few details 
					processQuery.step1();
					
					//now lets iterate over docIDTokens -> docid + csv(query tokens)
					processQuery.step2();
						
					//print to file
					processQuery.step3(index,sCurrentLine);
					
					//
					processQuery.step4(index,sCurrentLine);
					processQuery.step5(sCurrentLine);
					
					try {
						processQuery.cleartables();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// query process ends
				
				docID++;
				index++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		//return this.tokenList;
	}
	
	public void sortTokenList(){
		Collections.sort(this.getTokenList(), new DocIDComparator());
		Collections.sort(this.getTokenList(), new TokenComparator());
	}

	public ArrayList<TokenInfo> getTokenList() {
		return tokenList;
	}

	public void setTokenList(ArrayList<TokenInfo> tokenList) {
		this.tokenList = tokenList;
	}

	
	/**
	 * @return the finalScores
	 */
	public ArrayList<QueryResults> getFinalScores() {
		return finalScores;
	}

	/**
	 * @param finalScores the finalScores to set
	 */
	public void setFinalScores(ArrayList<QueryResults> finalScores) {
		this.finalScores = finalScores;
	}

	/**
	 * @return the processQuery
	 */
	public ProcessQuery getProcessQuery() {
		return processQuery;
	}

	/**
	 * @param processQuery the processQuery to set
	 */
	public void setProcessQuery(ProcessQuery processQuery) {
		this.processQuery = processQuery;
	}

}
