/**
 * 
 */
package irsystem.sort;

import irsystem.tokenizer.TokenInfo;

import java.util.Comparator;


public class DocIDComparator implements Comparator<TokenInfo>{

	@Override
	public int compare(TokenInfo arg0, TokenInfo arg1) {
		// TODO Auto-generated method stub
		return arg0.getDocID().compareTo(arg1.getDocID());
	}
	
}
