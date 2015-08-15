/**
 * 
 */
package irsystem.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

	BufferedWriter writer = null;

	public DataWriter(String fileNameIn) {
		try {
			this.writer = new BufferedWriter(new FileWriter(fileNameIn));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(String dataIn) {
		try {
			writer.write(dataIn);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeLn(String dataIn) {
		try {
			writer.write(dataIn);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
