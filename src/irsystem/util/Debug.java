/**
 * 
 */
package irsystem.util;

public class Debug {

	private static int DEBUG_VALUE;
	private static int count_offset_VALUE;
	private static int docID_VALUE;
	private static int numberofDocs_VALUE;
	private static double okapi_k1_VALUE;
	private static double okapi_k3_VALUE;
	private static double okapi_b_VALUE;
	
	/**
	 * Setter method(mutator) for DEBUG_VALUE
	 * 
	 * @param value
	 *            - set the DEBUG_VALUE to this
	 */

	public static void setDebugValue(int value) {

		DEBUG_VALUE = value;

	}

	/**
	 * Getter(accessor) for DEBUG_VALUE
	 * 
	 * @return - returns the DEBUG_VALUE
	 */

	public static int getDebugValue() {

		return DEBUG_VALUE;

	}


	/**
	 * Prints the contents as per the debug value
	 * 
	 * @param value
	 *            - DEBUG_VALUE
	 * @param Message
	 *            - message to print
	 */

	public static void printDebug(int value, String Message) {

		if (getDebugValue() == value) {
			switch (value) {
			case 0:
			case 1:
			case 2:
				System.out.print(Message);
				break;
			
			default:
				System.err.println(Message);
				System.exit(-1);
				break;
			}
		}
	}
	
	public static void printDebugLn(int value, String Message) {

		if (getDebugValue() == value) {
			switch (value) {
			case 0:
			case 1:
			case 2:
				System.out.println(Message);
				break;
			default:
				System.err.println(Message);
				System.exit(-1);
				break;
			}
		}
	}
	
	/**
	 * Prints the error message when the debug value is not set
	 * @param Message
	 */
	public static void printDebug(String Message){
		System.err.println(Message);
		System.exit(-1);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Debug []";
	}

	/**
	 * @return the count_offset_VALUE
	 */
	public static int getCount_offset_VALUE() {
		return count_offset_VALUE;
	}

	/**
	 * @param count_offset_VALUE the count_offset_VALUE to set
	 */
	public static void setCount_offset_VALUE(int count_offset_VALUE) {
		Debug.count_offset_VALUE = count_offset_VALUE;
	}

	/**
	 * @return the docID_VALUE
	 */
	public static int getDocID_VALUE() {
		return docID_VALUE;
	}

	/**
	 * @param docID_VALUE the docID_VALUE to set
	 */
	public static void setDocID_VALUE(int docID_VALUE) {
		Debug.docID_VALUE = docID_VALUE;
	}

	/**
	 * @return the numberofDocs_VALUE
	 */
	public static int getNumberofDocs_VALUE() {
		return numberofDocs_VALUE;
	}

	/**
	 * @param numberofDocs_VALUE the numberofDocs_VALUE to set
	 */
	public static void setNumberofDocs_VALUE(int numberofDocs_VALUE) {
		Debug.numberofDocs_VALUE = numberofDocs_VALUE;
	}

	/**
	 * @return the okapi_k1_VALUE
	 */
	public static double getOkapi_k1_VALUE() {
		return okapi_k1_VALUE;
	}

	/**
	 * @param okapi_k1_VALUE the okapi_k1_VALUE to set
	 */
	public static void setOkapi_k1_VALUE(double okapi_k1_VALUE) {
		Debug.okapi_k1_VALUE = okapi_k1_VALUE;
	}

	/**
	 * @return the okapi_k3_VALUE
	 */
	public static double getOkapi_k3_VALUE() {
		return okapi_k3_VALUE;
	}

	/**
	 * @param okapi_k3_VALUE the okapi_k3_VALUE to set
	 */
	public static void setOkapi_k3_VALUE(double okapi_k3_VALUE) {
		Debug.okapi_k3_VALUE = okapi_k3_VALUE;
	}

	/**
	 * @return the okapi_b_VALUE
	 */
	public static double getOkapi_b_VALUE() {
		return okapi_b_VALUE;
	}

	/**
	 * @param okapi_b_VALUE the okapi_b_VALUE to set
	 */
	public static void setOkapi_b_VALUE(double okapi_b_VALUE) {
		Debug.okapi_b_VALUE = okapi_b_VALUE;
	}
	
	
}
