/**
 * 
 */
package irsystem.driver;


import irsystem.util.Debug;
import irsystem.prequeryprocess.PreQuery;
import irsystem.processquery.ProcessQuery;

import irsystem.tokenizer.TokenInfo;
import irsystem.tokenizer.Tokenizer;
import irsystem.util.Helper;

/**
 * @author Rikhala Pravallika Reddy 
 * @author Aaditya Patil
 */

public class Driver {
	
	private static int debugValue = 1;	//Default Debug Value
	private static int countoffsetValue = 0;	//Default countoffsetValue Value
	private static int docIDValue = 0;	//Default docIDValue Value
	private static double okapi_k1 = 1.2;	//Default docIDValue Value
	private static double okapi_k3 = 1.2;	//Default docIDValue Value
	private static double okapi_b = 0.75;	//Default docIDValue Value
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Debug.setDebugValue(debugValue);
		Debug.setCount_offset_VALUE(countoffsetValue);
		Debug.setDocID_VALUE(docIDValue);
		Debug.setOkapi_k1_VALUE(okapi_k1);
		Debug.setOkapi_k3_VALUE(okapi_k3);
		Debug.setOkapi_b_VALUE(okapi_b);
		
		String inputName = "200_content.txt";
		String dictName = "dictionary.txt";
		String postName = "postings.txt";
		String queryName = "query.txt";

		/***
		 * 1. Tokenize the documents. Your tokenizer should include removing special symbols and punctuations 
		 * 	  (apostrophes, hyphens, periods in numbers, space between numbe rs, parentheses, etc.), 
		 *    case-folding, and stemming (you are allowed to use Porter’s stemmer). The resulting tokens will be called terms.
		 *    
		 * 2. Generate (term, docID) pairs for all the terms produced by your tokenizer.
		 */
		
		Tokenizer tokens = new Tokenizer(inputName);
		tokens.createToken(1);
		
		/***
		 * 3. Sort the (term, docID) pairs by term in numerical-alphabetic order.
		 */
		tokens.sortTokenList();

		Debug.printDebugLn(1,"length of tokenlist is : " + tokens.getTokenList().size());

		
		
		/***
		 * 4. Merge (term, docID) pairs that have the same term into a structure of the format: (term, df,postings-list), 
		 * 	  where df is the document frequency of the term and the postings-list is a sequence of the pairs of the format: (docID, tf), 
		 *    where tf is the term frequency of the term in the document (i.e., the number of times the term appears in the document) 
		 *    identified by the docID. The postings list for each term is sorted in ascending docIDs.
		 */
		Helper helper = new Helper();
		helper.merge(tokens.getTokenList());
		TokenInfo tokenObj = helper.getTokenInfoObj();
		
		
		/***
		 * 5. Generate output. The output of this part of the project consists of two files: dictionary.txt and postings.txt. 
		 *    Each line in dictionary.txt corresponds to one term and it has three fields: term, df, offset), 
		 *    where offset refers to the location of the postings list (the start of the postings list) in the postings.txt. 
		 *    Each line in postings.txt has two fields: docID and tfwt.(revised posting.txt) 
		 *    If the postings list of a term has k postings, it will have k consecutive lines in postings.txt sorted by docIDs.
		 */
		helper.generateFiles(dictName, postName, helper.getTokenList());
		
		Debug.printDebugLn(1,"length of token/term dict is : " + tokenObj.getTokenDict().size());
		
		// End of Project part 1
		
		/***
		 * Start of Project Part 2
		 */
		PreQuery preQueryobj = new PreQuery();
		
		/***
		 * Convert each term frequency value in the postings lists to a tf weight value using formula 1 + log10(tf t,d)
		 */
		preQueryobj.processStep1(tokenObj.getTokenDict());
		
		Debug.printDebugLn(1,"number of documents parsed is : " + preQueryobj.getTermDetails().size());
		Debug.setNumberofDocs_VALUE(preQueryobj.getTermDetails().size());
		
		/***
		 * The document length normalization factor(1/||D||) for each document is computed in advanced and all the normalization 
		 * factors are stored in an array. Only tf weight are used to compute the normalization factor.
		 */
		preQueryobj.processStep2();
		
		
		/***
		 * When query comes we first tokenize the terms in query then we retrieve docID's all the terms in query has.
		 * Then we iterate over all the docID's to find the score for each document for the query.
		 * And finally we store each query-docID scores in the data structure and print it in txt file.
		 * To find the score we multiple the weight of query term and the normalized document term value and 
		 * then add it for all terms in the query.
		 */
		ProcessQuery processQuery = new ProcessQuery(tokenObj.getTokenDict(),preQueryobj);
		Tokenizer queryTokens = new Tokenizer(queryName,processQuery);
		queryTokens.createToken(2);
		
		Debug.printDebugLn(2,"length of new array is : " + preQueryobj.getNormFact().length);
		
	}

}
