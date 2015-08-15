package irsystem.sort;

import irsystem.merge.PostingList;

import java.util.Comparator;

public class DocIDPostingComparator implements Comparator<PostingList>{

	@Override
	public int compare(PostingList arg0, PostingList arg1) {
		// TODO Auto-generated method stub
		return arg0.getDocID().compareTo(arg1.getDocID());
	}
	
}

