# Info-Retrieval Project Readme
Implemented different components of Information Retrieval in Java

1.	The project part 1 is implemented in Java. Each sub-task has a .java file implemented under the “src”  folder like “merge”, “sort”, “tokenizer” etc.

2.	The project part 2 has been continued in the same project, after we create the dictionary and posting file we continue with part 2 of the project.


3.	We first fixed the issues in Project Part 1 submission that is now our dictionary.txt and posting.txt matches the given files as reference in part 2 that is dictionary.txt and posting.txt.

4.	Now, we have also replaced the data structure by hashtable, as we need a fast access data structure.


5.	We first make sure we do the following to things : 
  1.	Convert each term frequency value in the postings lists to a tf weight value using formula 1 + log10(tf t,d).

  2.	The document length normalization factor(1/||D||) for each document is computed in advanced and all the normalization           factors are stored in an array. Only tf weight are used to compute the normalization factor.

6.	The following are the steps we follow when query comes we first :
  1.	Tokenize the terms in query then we retrieve docID's all the terms in query has. 

  2.	Then we iterate over all the docID's to find the score for each document for the query. 
  3.	And finally we store each query-docID scores in the data structure and print it in txt file.
  4.	To find the score we have implemented Document-At-A-Time algorithm for processing vector space queries based on the Cosine       similarity function, we multiple the weight of query term and the normalized document term value and then add it for all        terms in the query. We didn’t use idf for calculating the normalized document term values. We used only weight of query         term instead of normalized one’s.

7.	Finally we only show top 10 results for the queries in their respective text files.
8.	Adding points to the report submitted earlier, in this part we implemented one more similarity function Okapi function.
9.	We re-ran the indexing, made a larger hashtable to print more data in the required format.

10	.step2() method in ProcessQuery.java has been altered to calculate the okapi similarity. Also, it creates the larger             hashtable to print all required data as mentioned in point no 3.
11. In step5()method in ProcessQuery.java we calculated MAP@5 and MAP@10. We found that the values are coming out to be same for     both similarity methods.
     

Note: Wherever we have a search / access we have made sure it is on a hashtable so that the access time is fast.

Config:
In Driver.java (irsystem.driver) we have three variables:
debugValue – used for debugging messages of different levels:
0-	only main output of queries search results.
1-	Output with debugging messages
2-	More detailed debugging messages
countoffsetValue
0-	to start the offset value from 0
1-	to start the offset value from 1
docIDValue
0-	to start the docID’s from 0
1-	to start the docID’s from 1
Added the following static values :
private static double okapi_k1 = 1.2;	
private static double okapi_k3 = 1.2;	
private static double okapi_b = 0.75;	

