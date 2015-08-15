/**
 * 
 */
package irsystem.sort;

import irsystem.queryresults.QueryResults;

import java.util.Comparator;


public class ScoreComparator implements Comparator<QueryResults>{

	@Override
	public int compare(QueryResults arg1, QueryResults arg0) {
		// TODO Auto-generated method stub
		return arg0.getScore().compareTo(arg1.getScore());
	}

}
