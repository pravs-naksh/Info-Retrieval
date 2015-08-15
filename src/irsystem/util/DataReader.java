/**
 * 
 */
package irsystem.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
	
	private String fileName;
	private BufferedReader br = null;
	
	public DataReader(String fileNameIn){
		this.fileName = fileNameIn;
		openFile();
	}
	
	public BufferedReader getBR(){
		return br;
	}
	
	/**
	 * This method opens the file
	 */
	public void openFile() {
		try {
			br = new BufferedReader(new FileReader(this.fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
	/**
	 * This method will close the file
	 */
	public void closeFile(){
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			
		}
	}
	
	
}
